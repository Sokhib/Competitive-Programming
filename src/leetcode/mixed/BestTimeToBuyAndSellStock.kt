package leetcode.mixed

class Solution121 {
    fun maxProfit(prices: IntArray): Int {
        var res = 0
        var min = Integer.MAX_VALUE
        var max = 0

        prices.forEach { value ->
            if (value < min) {
                min = value
                max = min
            }
            if (value > max) {
                max = value
                res = Math.max(res, max - min)
            }
        }
        return res
    }
}

fun main() {
    val sol121 = Solution121()
    println(sol121.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))
    println(sol121.maxProfit(intArrayOf(7, 6, 5, 4)))
    println(sol121.maxProfit(intArrayOf(3, 7, 1, 2)))


}