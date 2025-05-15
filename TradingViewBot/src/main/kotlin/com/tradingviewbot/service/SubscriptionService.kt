package com.tradingviewbot.service

import com.tradingviewbot.model.Subscription
import mu.KotlinLogging
import java.util.concurrent.ConcurrentHashMap

private val logger = KotlinLogging.logger {}

/**
 * Service for managing user subscriptions
 */
class SubscriptionService {

    // In-memory storage for subscriptions
    // In a production environment, this would be stored in a database
    private val subscriptions = ConcurrentHashMap<String, Subscription>()

    /**
     * Adds a new subscription
     *
     * @param subscription The subscription to add
     * @return true if the subscription was added, false if it already existed
     */
    fun addSubscription(subscription: Subscription): Boolean {
        val uniqueId = subscription.getUniqueId()

        if (subscriptions.containsKey(uniqueId)) {
            logger.info { "Subscription already exists: $uniqueId" }
            return false
        }

        subscriptions[uniqueId] = subscription
        logger.info { "Added subscription: $uniqueId" }
        return true
    }

    /**
     * Removes a subscription
     *
     * @param chatId The chat ID of the user
     * @param symbol The symbol to unsubscribe from
     * @return true if the subscription was removed, false if it didn't exist
     */
    fun removeSubscription(chatId: String, symbol: String): Boolean {
        val uniqueId = "$chatId:$symbol"

        if (!subscriptions.containsKey(uniqueId)) {
            logger.info { "Subscription not found: $uniqueId" }
            return false
        }

        subscriptions.remove(uniqueId)
        logger.info { "Removed subscription: $uniqueId" }
        return true
    }

    /**
     * Gets all subscriptions for a specific user
     *
     * @param chatId The chat ID of the user
     * @return List of subscriptions for the user
     */
    fun getSubscriptionsForUser(chatId: String): List<Subscription> {
        return subscriptions.values
            .filter { it.chatId == chatId }
            .toList()
    }

    /**
     * Gets all subscriptions
     *
     * @return List of all subscriptions
     */
    fun getAllSubscriptions(): List<Subscription> {
        return subscriptions.values.toList()
    }

    /**
     * Gets a specific subscription
     *
     * @param chatId The chat ID of the user
     * @param symbol The symbol of the subscription
     * @return The subscription, or null if not found
     */
    fun getSubscription(chatId: String, symbol: String): Subscription? {
        val uniqueId = "$chatId:$symbol"
        return subscriptions[uniqueId]
    }

    /**
     * Checks if a subscription exists
     *
     * @param chatId The chat ID of the user
     * @param symbol The symbol to check
     * @return true if the subscription exists, false otherwise
     */
    fun hasSubscription(chatId: String, symbol: String): Boolean {
        val uniqueId = "$chatId:$symbol"
        return subscriptions.containsKey(uniqueId)
    }

    /**
     * Gets the count of all subscriptions
     *
     * @return The number of subscriptions
     */
    fun getSubscriptionCount(): Int {
        return subscriptions.size
    }
}