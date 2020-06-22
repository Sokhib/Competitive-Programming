package leetcode.maychallenge

import kotlin.test.assertEquals

private object ValidPerfectSquare {
    fun isPerfectSquare(num: Int): Boolean {
        val a = Math.sqrt(num.toDouble())
        val b = Math.sqrt(num.toDouble()).toInt().toDouble()
        if (a == b) return true
        return false
    }
}

fun main() {
    assertEquals(true, ValidPerfectSquare.isPerfectSquare(16))
    assertEquals(false, ValidPerfectSquare.isPerfectSquare(15))
}