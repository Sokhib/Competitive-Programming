package Leetcode.AprilChallenge

object BestTimeToBuyAndSellStock {
    fun maxProfit(prices: IntArray): Int {
        var sum = 0
        var buy = Integer.MAX_VALUE
        var sell = 0
        for (i in prices.indices) {
            if (prices[i] < buy) {
                if (sell > buy) sum += (sell - buy)
                buy = prices[i]
                sell = buy
            }
            if (prices[i] > sell) {
                sell = prices[i]
            } else {
                if (buy < sell) {
                    sum += (sell - buy)
                    buy = prices[i]
                    sell = prices[i]
                }
            }
            if (i == prices.size - 1) {
                if (sell > buy) {
                    sum += (sell - buy)
                }
            }
        }
        return sum
    }
}

fun main() {
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(2, 4, 1)).also(::println)
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)).also(::println)
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(1, 2, 3, 4, 5)).also(::println)
    BestTimeToBuyAndSellStock.maxProfit(intArrayOf(7, 6, 4, 3, 1)).also(::println)
}