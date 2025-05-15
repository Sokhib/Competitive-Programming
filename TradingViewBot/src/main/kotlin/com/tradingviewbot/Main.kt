package com.tradingviewbot

import com.tradingviewbot.bot.TradingViewBot
import com.tradingviewbot.config.BotConfig
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    logger.info { "Starting TradingView Bot..." }

    try {
        // Load configuration
        val config = BotConfig.load()

        // Initialize and start the bot
        val bot = TradingViewBot(config)
        bot.start()

        logger.info { "Bot started successfully!" }

        // Keep the application running
        Runtime.getRuntime().addShutdownHook(Thread {
            logger.info { "Shutting down bot..." }
            bot.stop()
        })

        // Block the main thread to keep the application running
        Thread.currentThread().join()
    } catch (e: Exception) {
        logger.error(e) { "Failed to start the bot: ${e.message}" }
    }
}