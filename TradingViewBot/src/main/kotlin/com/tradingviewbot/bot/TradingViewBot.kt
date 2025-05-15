package com.tradingviewbot.bot

import com.tradingviewbot.config.BotConfig
import com.tradingviewbot.model.Subscription
import com.tradingviewbot.service.MarketDataService
import com.tradingviewbot.service.SubscriptionService
import mu.KotlinLogging
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

/**
 * Main bot class that handles Telegram interactions and market data monitoring
 */
class TradingViewBot(private val config: BotConfig) : TelegramLongPollingBot() {

    private val subscriptionService = SubscriptionService()
    private val marketDataService = MarketDataService()
    private val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

    override fun getBotToken(): String = config.botToken

    override fun getBotUsername(): String = config.botUsername

    /**
     * Starts the bot and initializes the scheduled task for checking price crossings
     */
    fun start() {
        logger.info { "Starting TradingView Bot..." }

        try {
            // Register the bot
            val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
            botsApi.registerBot(this)
            logger.info { "Bot registered successfully" }

            // Schedule the task to check for EMA crossings
            scheduler.scheduleAtFixedRate(
                { checkEmaCrossings() },
                0,
                config.pollingIntervalMinutes.toLong(),
                TimeUnit.MINUTES
            )

            logger.info { "Scheduled EMA crossing check every ${config.pollingIntervalMinutes} minutes" }
        } catch (e: TelegramApiException) {
            logger.error(e) { "Failed to start bot: ${e.message}" }
            throw e
        }
    }

    /**
     * Stops the bot and shuts down the scheduler
     */
    fun stop() {
        logger.info { "Stopping bot..." }
        scheduler.shutdown()
        try {
            if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
                scheduler.shutdownNow()
            }
        } catch (e: InterruptedException) {
            scheduler.shutdownNow()
            Thread.currentThread().interrupt()
        }
        logger.info { "Bot stopped" }
    }

    /**
     * Handles incoming updates from Telegram
     */
    override fun onUpdateReceived(update: Update) {
        if (!update.hasMessage() || !update.message.hasText()) return

        val chatId = update.message.chatId.toString()
        val messageText = update.message.text

        logger.info { "Received message from $chatId: $messageText" }

        try {
            when {
                messageText.startsWith("/start") -> handleStartCommand(chatId)
                messageText.startsWith("/help") -> handleHelpCommand(chatId)
                messageText.startsWith("/subscribe") -> handleSubscribeCommand(chatId, messageText)
                messageText.startsWith("/unsubscribe") -> handleUnsubscribeCommand(chatId, messageText)
                messageText.startsWith("/list") -> handleListCommand(chatId)
                else -> sendMessage(chatId, "Unknown command. Type /help for available commands.")
            }
        } catch (e: Exception) {
            logger.error(e) { "Error processing message: ${e.message}" }
            sendMessage(chatId, "Error processing your request: ${e.message}")
        }
    }

    /**
     * Handles the /start command
     */
    private fun handleStartCommand(chatId: String) {
        val message = """
            Welcome to TradingView Bot! 
            
            This bot allows you to subscribe to stocks, crypto coins, and forex pairs from TradingView.
            When the EMA 8 crosses the price, you'll receive a notification.
            
            Type /help to see available commands.
        """.trimIndent()

        sendMessage(chatId, message)
    }

    /**
     * Handles the /help command
     */
    private fun handleHelpCommand(chatId: String) {
        val message = """
            Available commands:
            
            /subscribe <type> <symbol> - Subscribe to a symbol
              Types: stock, crypto, forex
              Examples: 
                /subscribe stock AAPL
                /subscribe crypto BTC
                /subscribe forex EURUSD
                
            /unsubscribe <symbol> - Unsubscribe from a symbol
              Example: /unsubscribe AAPL
              
            /list - List all your subscriptions
            
            /help - Show this help message
        """.trimIndent()

        sendMessage(chatId, message)
    }

    /**
     * Handles the /subscribe command
     */
    private fun handleSubscribeCommand(chatId: String, messageText: String) {
        val parts = messageText.split(" ")
        if (parts.size < 3) {
            sendMessage(chatId, "Invalid format. Use: /subscribe <type> <symbol>")
            return
        }

        val type = parts[1].lowercase()
        val symbol = parts[2].uppercase()

        if (type !in listOf("stock", "crypto", "forex")) {
            sendMessage(chatId, "Invalid type. Use: stock, crypto, or forex")
            return
        }

        val subscription = Subscription(chatId, type, symbol)
        subscriptionService.addSubscription(subscription)

        sendMessage(chatId, "Successfully subscribed to $symbol ($type)")
    }

    /**
     * Handles the /unsubscribe command
     */
    private fun handleUnsubscribeCommand(chatId: String, messageText: String) {
        val parts = messageText.split(" ")
        if (parts.size < 2) {
            sendMessage(chatId, "Invalid format. Use: /unsubscribe <symbol>")
            return
        }

        val symbol = parts[1].uppercase()
        val removed = subscriptionService.removeSubscription(chatId, symbol)

        if (removed) {
            sendMessage(chatId, "Successfully unsubscribed from $symbol")
        } else {
            sendMessage(chatId, "You are not subscribed to $symbol")
        }
    }

    /**
     * Handles the /list command
     */
    private fun handleListCommand(chatId: String) {
        val subscriptions = subscriptionService.getSubscriptionsForUser(chatId)

        if (subscriptions.isEmpty()) {
            sendMessage(chatId, "You don't have any active subscriptions")
            return
        }

        val message = StringBuilder("Your subscriptions:\n\n")
        subscriptions.forEach { subscription ->
            message.append("- ${subscription.symbol} (${subscription.type})\n")
        }

        sendMessage(chatId, message.toString())
    }

    /**
     * Checks for EMA crossings for all subscriptions
     */
    private fun checkEmaCrossings() {
        logger.info { "Checking EMA crossings for all subscriptions" }

        try {
            val allSubscriptions = subscriptionService.getAllSubscriptions()

            for (subscription in allSubscriptions) {
                try {
                    val hasCrossed = marketDataService.checkEmaCrossing(subscription.type, subscription.symbol)

                    if (hasCrossed) {
                        val price = marketDataService.getCurrentPrice(subscription.type, subscription.symbol)
                        val message = "EMA 8 has crossed the price for ${subscription.symbol}!\nCurrent price: $price"
                        sendMessage(subscription.chatId, message)
                    }
                } catch (e: Exception) {
                    logger.error(e) { "Error checking EMA crossing for ${subscription.symbol}: ${e.message}" }
                }
            }
        } catch (e: Exception) {
            logger.error(e) { "Error in EMA crossing check: ${e.message}" }
        }
    }

    /**
     * Sends a message to a chat
     */
    private fun sendMessage(chatId: String, text: String) {
        try {
            val message = SendMessage()
            message.chatId = chatId
            message.text = text
            execute(message)
        } catch (e: TelegramApiException) {
            logger.error(e) { "Failed to send message to $chatId: ${e.message}" }
        }
    }
}