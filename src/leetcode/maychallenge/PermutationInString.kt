package leetcode.maychallenge

import kotlin.test.assertEquals


private fun checkInclusion(s1: String, s2: String): Boolean {
    val s1Len = s1.length
    val s2Len = s2.length
    if (s1Len > s2Len) return false
    val count = IntArray(26) { 0 }

    for (c in s1) {
        count[c - 'a'] -= -1
    }
    for (i in 0 until s2Len) {
        count[s2[i] - 'a']--
        if (i - s1Len >= 0) count[s2[i - s1Len] - 'a']++
        if (count.all { it == 0 }) return true
    }
    return false
}

fun main() {
    assertEquals(true, checkInclusion("ab", "dsadsaab"))
}