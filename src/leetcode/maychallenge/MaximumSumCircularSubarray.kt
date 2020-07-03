package leetcode.maychallenge

import kotlin.test.assertEquals

private object MaximumSumCircularSubarray {
    fun maxSubarraySumCircular(A: IntArray): Int {
        val dp = IntArray(A.size) { 0 }
        val prefixSum = IntArray(A.size) { 0 }
        val suffixSum = IntArray(A.size) { 0 }

        var maxFromSuffixPrefix = Integer.MIN_VALUE
        var maxx = Integer.MIN_VALUE
        A.forEachIndexed { index, value ->
            //dp
            dp[index] = if (index == 0) A[0] else value.coerceAtLeast(dp[index - 1] + value)
            if (dp[index] > maxx) maxx = dp[index]
            //prefix
            prefixSum[index] = if (index == 0) A[0] else prefixSum[index - 1] + A[index]
        }
        //suffix
        for (index in A.size - 1 downTo 0) {
            suffixSum[index] = if (index == A.size - 1) A[A.size - 1] else suffixSum[index + 1] + A[index]
        }
        var maxPrefix = prefixSum[0]
        for (index in 1 until A.size) {
            maxFromSuffixPrefix =
                (suffixSum[index] + maxPrefix).coerceAtLeast(maxFromSuffixPrefix)
            maxPrefix = maxPrefix.coerceAtLeast(prefixSum[index - 1])
        }

        return maxx.coerceAtLeast(maxFromSuffixPrefix)
    }
}

fun main() {
    assertEquals(-1, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(-2, -3, -1)))
    assertEquals(10, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(5, -3, 5)))
    assertEquals(14, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(-5, -2, 5, 6, -2, -7, 0, 2, 8)))
    assertEquals(17, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(-2, -8, 6, 9, 2)))
    assertEquals(8, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(-5, 3, 5)))
    assertEquals(3, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(3, -2, 2, -3)))
    assertEquals(4, MaximumSumCircularSubarray.maxSubarraySumCircular(intArrayOf(3, -1, 2, -1)))
}