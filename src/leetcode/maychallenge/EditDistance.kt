package leetcode.maychallenge

import kotlin.math.abs
import kotlin.test.assertEquals

private fun minDistance(word1: String, word2: String): Int {
    val n = word2.length
    val m = word1.length
    var answer = abs(n - m)

    return answer
}

fun main() {
    assertEquals(5, minDistance("intention", "execution"))
    assertEquals(3, minDistance("horse", "ros"))
    assertEquals(2, minDistance("ab", "ba"))
}