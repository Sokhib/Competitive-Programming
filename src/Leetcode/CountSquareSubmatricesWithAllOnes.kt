package Leetcode

object Solution1277 {

    fun countSquares(matrix: Array<IntArray>): Int {
        var sum = 0
        var flag = true
        var kValue = 0
        val rowCount = matrix.size
        val columnCount = matrix[0].size
        var min: Int
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 1) {
                    min = Math.min(columnCount - j, rowCount - i)
                    for (k in i until i + min) {
                        kValue = k
                        for (l in j until j + min) {
                            if (matrix[k][l] != 1) {
                                flag = false

                            }

                        }
                    }
                    if (flag) sum += kValue
                }

            }
        }
        return sum
    }


}

fun main() {
//    println(
//        Solution1277.countSquares(
//            arrayOf(
//                intArrayOf(1, 1, 1),
//                intArrayOf(0, 1, 1)
//            )
//        )
//    )
//    println(
//        Solution1277.countSquares(
//            arrayOf(
//                intArrayOf(1, 1),
//                intArrayOf(1, 1)
//            )
//        )
//    )
    println(
        Solution1277.countSquares(
            arrayOf(
                intArrayOf(0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1),
                intArrayOf(0, 1, 1, 1)
            )
        )
    )

}