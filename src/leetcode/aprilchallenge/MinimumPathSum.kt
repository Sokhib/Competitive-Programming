package leetcode.aprilchallenge

object MinimumPathSum {
    fun minPathSum(grid: Array<IntArray>): Int {
        val dp = Array(grid.size) { IntArray(grid[0].size) { 0 } }
        if (grid.isEmpty()) return 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0]
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1]
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j]
                } else dp[i][j] = grid[i][j] + dp[i - 1][j].coerceAtMost(dp[i][j - 1])
            }
        }
        return dp[grid.size - 1][grid[0].size - 1]
    }
}

fun main() {
    MinimumPathSum.minPathSum(
        arrayOf(
            intArrayOf(1, 3, 1),
            intArrayOf(1, 5, 1),
            intArrayOf(4, 2, 1)
        )
    ).also(::println)
}