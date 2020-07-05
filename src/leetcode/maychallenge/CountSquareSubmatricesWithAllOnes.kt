package leetcode.maychallenge

import kotlin.test.assertEquals

private fun countSquares(matrix: Array<IntArray>): Int {
    var answer = 0
    val row = matrix.size
    val column = matrix[0].size
    for (i in 0 until row) {
        for (j in 0 until column) {
            if (matrix[i][j] == 1) ++answer
            if (checkForBorder(i, j, row, column) && matrix[i][j] == 1) {
                matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1
                answer += matrix[i][j] - 1
            }
        }
    }

    return answer
}

fun checkForBorder(i: Int, j: Int, H: Int, W: Int) = i < H && j < W && i > 0 && j > 0
fun main() {
    assertEquals(
        6,
        countSquares(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(0, 1, 1)
            )
        )
    )
    assertEquals(
        5,
        countSquares(
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 1)
            )
        )
    )
    assertEquals(
        15, countSquares(
            arrayOf(
                intArrayOf(0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(0, 1, 1, 1)
            )
        )
    )

}