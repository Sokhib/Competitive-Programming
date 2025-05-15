package com.tradingviewbot.model

import java.time.Instant

/**
 * Represents a user's subscription to a specific asset
 *
 * @property chatId The Telegram chat ID of the user
 * @property type The type of asset (stock, crypto, forex)
 * @property symbol The symbol of the asset (e.g., AAPL, BTC, EURUSD)
 * @property createdAt The timestamp when the subscription was created
 */
data class Subscription(
    val chatId: String,
    val type: String,
    val symbol: String,
    val createdAt: Instant = Instant.now()
) {
    /**
     * Returns a unique identifier for this subscription
     */
    fun getUniqueId(): String = "$chatId:$symbol"
}