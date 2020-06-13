package leetcode.aprilchallenge

private object MaximalSquare {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0
        var maxx = 0
        val rowCount = matrix.size
        val columnCount = matrix[0].size
        val dp = Array(rowCount) { Array(columnCount) { 0 } }
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1
                    if (i > 0 && j > 0)
                        dp[i][j] += dp[i][j - 1].coerceAtMost(dp[i - 1][j]).coerceAtMost(dp[i - 1][j - 1])

                }
                maxx = maxx.coerceAtLeast(dp[i][j])
            }
        }
        return maxx * maxx
    }
}

fun main() {
    MaximalSquare.maximalSquare(
        arrayOf(
            charArrayOf('1', '1', '0', '1'),
            charArrayOf('1', '1', '0', '1'),
            charArrayOf('1', '1', '1', '1')
        )
    ).also(::println)

//    MaximalSquare.maximalSquare(
//        arrayOf(
//            charArrayOf('0', '0', '0', '1', '0', '1', '1', '1'),
//            charArrayOf('0', '1', '1', '0', '0', '1', '0', '1'),
//            charArrayOf('1', '0', '1', '1', '1', '1', '0', '1'),
//            charArrayOf('0', '0', '0', '1', '0', '0', '0', '0'),
//            charArrayOf('0', '0', '1', '0', '0', '0', '1', '0'),
//            charArrayOf('1', '1', '1', '0', '0', '1', '1', '1'),
//            charArrayOf('1', '0', '0', '1', '1', '0', '0', '1'),
//            charArrayOf('0', '1', '0', '0', '1', '1', '0', '0'),
//            charArrayOf('1', '0', '0', '1', '0', '0', '0', '0')
//        )
//    ).also(::println)
//
//    MaximalSquare.maximalSquare(
//        arrayOf(
//            charArrayOf('1', '0', '1', '0', '0'),
//            charArrayOf('1', '0', '1', '1', '1'),
//            charArrayOf('1', '1', '1', '1', '1'),
//            charArrayOf('1', '0', '0', '1', '0')
//        )
//    ).also(::println)

}