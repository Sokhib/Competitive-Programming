package leetcode.maychallenge

import kotlin.test.assertEquals

private object CheckIfItIsStraightLine {
    fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
        val xDiff = coordinates[1][0] - coordinates[0][0]
        val yDiff = coordinates[1][1] - coordinates[0][1]
        for (index in 2 until coordinates.size) {
            val xDelta = coordinates[index][0] - coordinates[index - 1][0]
            val yDelta = coordinates[index][1] - coordinates[index - 1][1]
            if (xDiff * yDelta != yDiff * xDelta) return false
        }
        return true
    }
}

fun main() {
    assertEquals(
        true, CheckIfItIsStraightLine.checkStraightLine(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 1),
                intArrayOf(0, -1)
            )
        )
    )

    assertEquals(
        false, CheckIfItIsStraightLine.checkStraightLine(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 5),
                intArrayOf(5, 0),
                intArrayOf(1337, 0),
                intArrayOf(0, 1337)
            )
        )
    )
}