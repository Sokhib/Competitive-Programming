package com.tradingviewbot.config

import mu.KotlinLogging
import java.io.FileInputStream
import java.util.Properties

private val logger = KotlinLogging.logger {}

/**
 * Configuration class for the TradingView Bot
 */
class BotConfig private constructor(
    val botToken: String,
    val botUsername: String,
    val pollingIntervalMinutes: Int
) {
    companion object {
        private const val CONFIG_FILE = "bot.properties"
        private const val DEFAULT_POLLING_INTERVAL = 5 // 5 minutes

        /**
         * Loads configuration from the properties file
         */
        fun load(): BotConfig {
            logger.info { "Loading bot configuration from $CONFIG_FILE" }

            val properties = Properties()
            try {
                // Try to load from the file
                val inputStream = FileInputStream(CONFIG_FILE)
                properties.load(inputStream)
                inputStream.close()
            } catch (e: Exception) {
                logger.warn { "Could not load configuration file: ${e.message}. Using environment variables." }
                // If file not found, try to use environment variables
            }

            // Get bot token from properties or environment variable
            val botToken = properties.getProperty("bot.token")
                ?: System.getenv("BOT_TOKEN")
                ?: throw IllegalStateException("Bot token not found. Set it in $CONFIG_FILE or as BOT_TOKEN environment variable")

            // Get bot username from properties or environment variable
            val botUsername = properties.getProperty("bot.username")
                ?: System.getenv("BOT_USERNAME")
                ?: throw IllegalStateException("Bot username not found. Set it in $CONFIG_FILE or as BOT_USERNAME environment variable")

            // Get polling interval from properties or environment variable, or use default
            val pollingIntervalStr = properties.getProperty("polling.interval.minutes")
                ?: System.getenv("POLLING_INTERVAL_MINUTES")
            val pollingInterval = pollingIntervalStr?.toIntOrNull() ?: DEFAULT_POLLING_INTERVAL

            return BotConfig(botToken, botUsername, pollingInterval)
        }
    }
}