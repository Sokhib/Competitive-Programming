package leetcode.maychallenge

import kotlin.test.assertEquals

private fun maxUncrossedLines(A: IntArray, B: IntArray): Int {
    val n = A.size
    val m = B.size
    fun checkCorner(i: Int, j: Int) = i < n - 1 && j < m - 1
    val dp = Array(n) { IntArray(m) { 0 } }
    var ans = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (A[i] == B[j]) {
                dp[i][j]++
                if (checkCorner(i, j)) dp[i + 1][j + 1] = dp[i + 1][j + 1].coerceAtLeast(dp[i][j])
            } else {
                if (i < n - 1) dp[i + 1][j] = dp[i + 1][j].coerceAtLeast(dp[i][j])
                if (j < m - 1) dp[i][j + 1] = dp[i][j + 1].coerceAtLeast(dp[i][j])
            }
            ans = ans.coerceAtLeast(dp[i][j])
        }
    }
    return ans
}

fun main() {
    assertEquals(2, maxUncrossedLines(intArrayOf(1, 4, 2), intArrayOf(1, 2, 4)))
    assertEquals(3, maxUncrossedLines(intArrayOf(2, 5, 1, 2, 5), intArrayOf(10, 5, 2, 1, 5, 2)))
}
