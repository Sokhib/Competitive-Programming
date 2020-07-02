package leetcode.maychallenge

import kotlin.test.assertEquals

private object FindAllAnagrams {
    fun findAnagrams(s: String, p: String): List<Int> {
        val pLength = p.length
        val listOfIndices: MutableList<Int> = mutableListOf()
        val pp = p.toCharArray()
        pp.sort()
        for (i in 0..s.length - pLength) {
            val ss = s.slice(i until i + pLength).toCharArray()
            ss.sort()
            if (ss.contentEquals(pp)) {
                listOfIndices.add(i)
            }
        }
        return listOfIndices
    }
}

fun main() {
    assertEquals(listOf(0, 2, 4, 6), FindAllAnagrams.findAnagrams("ababababab", "aab"))
    assertEquals(listOf(0, 6), FindAllAnagrams.findAnagrams("cbaebabacd", "abc"))
    assertEquals(listOf(0, 1, 2), FindAllAnagrams.findAnagrams("abab", "ab"))

}