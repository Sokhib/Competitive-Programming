package leetcode.maychallenge

import kotlin.test.assertEquals

fun frequencySort(word: String): String {
    val charMap = HashMap<Char, Int>()
    var ans = ""
    for (s in word) {
        if (charMap.containsKey(s)) charMap[s] = charMap[s]!! + 1
        else charMap[s] = 1
    }
    charMap.toList().sortedBy { (_, value) -> value }.reversed().forEach {
        for (i in 0 until it.second) {
            ans += it.first
        }
    }

    return ans
}

fun main() {
    assertEquals("bbAa", frequencySort("Aabb"))
    assertEquals("eert", frequencySort("tere"))
}