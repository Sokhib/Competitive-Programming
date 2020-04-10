package Leetcode.AprilChallenge

object BestTimeToBuyAndSellStock {
    fun maxProfit(prices: IntArray): Int {
        var bestWithoutStock = 0
        var bestWithStock = Integer.MIN_VALUE
        for (i in prices.indices) {
            bestWithStock = Math.max(bestWithStock, bestWithoutStock - prices[i])
            bestWithoutStock = Math.max(bestWithoutStock, bestWithStock + prices[i])
        }
        return bestWithoutStock

    }
}

fun main() {
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(1, 2, 3, 4, 5)).also(::println)
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(2, 4, 1)).also(::println)
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)).also(::println)
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(7, 6, 4, 3, 1)).also(::println)
}