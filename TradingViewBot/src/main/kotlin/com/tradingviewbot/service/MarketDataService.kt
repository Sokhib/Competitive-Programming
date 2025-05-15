package com.tradingviewbot.service

import com.google.gson.JsonParser
import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

/**
 * Service for fetching market data and checking EMA crossings
 */
class MarketDataService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    // Cache for price data to reduce API calls
    private val priceCache = ConcurrentHashMap<String, PriceData>()

    // Cache for previous crossing state to detect changes
    private val crossingStateCache = ConcurrentHashMap<String, Boolean>()

    /**
     * Checks if the EMA 8 has crossed the price for a given symbol
     *
     * @param type The type of asset (stock, crypto, forex)
     * @param symbol The symbol to check
     * @return true if a crossing has occurred since the last check, false otherwise
     */
    fun checkEmaCrossing(type: String, symbol: String): Boolean {
        logger.info { "Checking EMA crossing for $symbol ($type)" }

        try {
            // Get the latest price data
            val priceData = fetchPriceData(type, symbol)

            // Calculate EMA 8
            val ema8 = calculateEMA(priceData.historicalPrices, 8)

            // Check if EMA 8 has crossed the price
            val currentPrice = priceData.currentPrice
            val hasCrossed = hasEmaCrossedPrice(ema8, currentPrice, symbol)

            logger.info { "EMA 8 for $symbol: $ema8, Current price: $currentPrice, Has crossed: $hasCrossed" }

            return hasCrossed
        } catch (e: Exception) {
            logger.error(e) { "Error checking EMA crossing for $symbol: ${e.message}" }
            throw e
        }
    }

    /**
     * Gets the current price for a symbol
     *
     * @param type The type of asset (stock, crypto, forex)
     * @param symbol The symbol to check
     * @return The current price as a string
     */
    fun getCurrentPrice(type: String, symbol: String): String {
        try {
            val priceData = fetchPriceData(type, symbol)
            return priceData.currentPrice.toString()
        } catch (e: Exception) {
            logger.error(e) { "Error getting current price for $symbol: ${e.message}" }
            throw e
        }
    }

    /**
     * Fetches price data for a symbol
     *
     * @param type The type of asset (stock, crypto, forex)
     * @param symbol The symbol to fetch data for
     * @return PriceData containing current price and historical prices
     */
    private fun fetchPriceData(type: String, symbol: String): PriceData {
        val cacheKey = "$type:$symbol"

        // Check if we have cached data that's less than 5 minutes old
        val cachedData = priceCache[cacheKey]
        if (cachedData != null && System.currentTimeMillis() - cachedData.timestamp < 5 * 60 * 1000) {
            logger.debug { "Using cached price data for $symbol" }
            return cachedData
        }

        logger.info { "Fetching price data for $symbol ($type)" }

        try {
            // In a real implementation, this would call an actual API
            // For this example, we'll use a simulated approach
            val priceData = when (type) {
                "crypto" -> fetchCryptoData(symbol)
                "stock" -> fetchStockData(symbol)
                "forex" -> fetchForexData(symbol)
                else -> throw IllegalArgumentException("Unsupported asset type: $type")
            }

            // Cache the data
            priceCache[cacheKey] = priceData

            return priceData
        } catch (e: Exception) {
            logger.error(e) { "Error fetching price data for $symbol: ${e.message}" }
            throw e
        }
    }

    /**
     * Fetches crypto data from an API
     *
     * In a real implementation, this would call a crypto API
     */
    private fun fetchCryptoData(symbol: String): PriceData {
        try {
            // Example API call to CoinGecko for crypto data
            val apiUrl =
                "https://api.coingecko.com/api/v3/coins/$symbol/market_chart?vs_currency=usd&days=1&interval=hourly"

            val request = Request.Builder()
                .url(apiUrl)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    // If API call fails, use simulated data for demonstration
                    logger.warn { "API call failed for $symbol, using simulated data" }
                    return simulatePriceData(symbol)
                }

                val jsonResponse = response.body?.string() ?: throw IOException("Empty response body")
                val jsonElement = JsonParser.parseString(jsonResponse)

                // Parse the response to get price data
                // This is a simplified example and would need to be adapted for the actual API response format
                val prices = jsonElement.asJsonObject.getAsJsonArray("prices")
                val historicalPrices = mutableListOf<Double>()

                for (i in 0 until prices.size()) {
                    val price = prices[i].asJsonArray[1].asDouble
                    historicalPrices.add(price)
                }

                val currentPrice = historicalPrices.last()

                return PriceData(currentPrice, historicalPrices, System.currentTimeMillis())
            }
        } catch (e: Exception) {
            logger.warn(e) { "Error fetching crypto data for $symbol, using simulated data: ${e.message}" }
            return simulatePriceData(symbol)
        }
    }

    /**
     * Fetches stock data from an API
     *
     * In a real implementation, this would call a stock API
     */
    private fun fetchStockData(symbol: String): PriceData {
        // For demonstration purposes, we'll use simulated data
        // In a real implementation, this would call a stock API like Alpha Vantage or Yahoo Finance
        return simulatePriceData(symbol)
    }

    /**
     * Fetches forex data from an API
     *
     * In a real implementation, this would call a forex API
     */
    private fun fetchForexData(symbol: String): PriceData {
        // For demonstration purposes, we'll use simulated data
        // In a real implementation, this would call a forex API
        return simulatePriceData(symbol)
    }

    /**
     * Simulates price data for demonstration purposes
     */
    private fun simulatePriceData(symbol: String): PriceData {
        // Generate a "random" but deterministic current price based on the symbol
        val seed = symbol.sumOf { it.code }
        val basePrice = (seed % 1000).toDouble() + 100.0

        // Add some randomness to simulate price changes
        val currentPrice = basePrice * (1.0 + (Math.random() - 0.5) * 0.02)

        // Generate historical prices
        val historicalPrices = mutableListOf<Double>()
        for (i in 0 until 24) {
            val historicalPrice = basePrice * (1.0 + (Math.random() - 0.5) * 0.1)
            historicalPrices.add(historicalPrice)
        }

        // Add the current price to the end of the historical prices
        historicalPrices.add(currentPrice)

        return PriceData(currentPrice, historicalPrices, System.currentTimeMillis())
    }

    /**
     * Calculates the Exponential Moving Average (EMA) for a list of prices
     *
     * @param prices List of historical prices
     * @param period The period for the EMA calculation
     * @return The calculated EMA value
     */
    private fun calculateEMA(prices: List<Double>, period: Int): Double {
        if (prices.size < period) {
            throw IllegalArgumentException("Not enough price data to calculate EMA")
        }

        // Calculate the multiplier
        val multiplier = 2.0 / (period + 1)

        // Calculate the initial SMA (Simple Moving Average)
        var ema = prices.take(period).average()

        // Calculate the EMA for the remaining prices
        for (i in period until prices.size) {
            ema = (prices[i] - ema) * multiplier + ema
        }

        return ema
    }

    /**
     * Checks if the EMA has crossed the price since the last check
     *
     * @param ema The current EMA value
     * @param price The current price
     * @param symbol The symbol being checked
     * @return true if a crossing has occurred since the last check, false otherwise
     */
    private fun hasEmaCrossedPrice(ema: Double, price: Double, symbol: String): Boolean {
        val cacheKey = symbol

        // Get the previous crossing state (EMA above price)
        val previousState = crossingStateCache[cacheKey] ?: false

        // Calculate the current state
        val currentState = ema > price

        // Update the cache
        crossingStateCache[cacheKey] = currentState

        // A crossing has occurred if the state has changed
        return previousState != currentState
    }

    /**
     * Data class to hold price information
     */
    private data class PriceData(
        val currentPrice: Double,
        val historicalPrices: List<Double>,
        val timestamp: Long
    )
}