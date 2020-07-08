package leetcode.maychallenge

import kotlin.test.assertEquals

private fun countBits(num: Int): IntArray {
    // I don't know how it works, but I know what it does :D 'kappa'
    fun bitCount(i: Int): Int {
        var i = i
        i -= i ushr 1 and 1431655765
        i = (i and 858993459) + (i ushr 2 and 858993459)
        i = i + (i ushr 4) and 252645135
        i += i ushr 8
        i += i ushr 16
        return i and 63
    }

    val ans = mutableListOf<Int>()
    for (everyNum in 0..num) {
        ans.add(bitCount(everyNum))
    }
    return ans.toIntArray()
}

fun main() {
//    5.toString(radix = 2).also(::println)
//    "101".toInt(radix = 2).also(::println)
    assertEquals(intArrayOf(0, 1, 1, 2, 1, 2), countBits(5))
}