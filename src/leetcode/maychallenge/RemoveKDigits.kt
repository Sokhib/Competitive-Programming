package leetcode.maychallenge

import kotlin.test.assertEquals

private object RemoveKDigits {
    fun removeKdigits(num: String, k: Int): String {
        var mNum = num
        val dp: HashMap<Char, String> = HashMap()
        for (i in mNum.indices) {
            if (dp[mNum[i]] == null) {
                dp[mNum[i]] = mNum.replaceRange(i..i, "")
            } else {
                dp[mNum[i]] = minOf(dp[mNum[i]]!!, mNum.replaceRange(i..i, ""))
            }

        }
        val sortedNums = dp.toList().sortedBy { (_, value) -> value }.toMap()
        val keys = sortedNums.keys.toList()
        for (i in 0 until k) {
            mNum = mNum.replaceFirst(keys[i].toString(), "")
        }
        while (mNum.isNotEmpty() && mNum[0] == '0')
            mNum = mNum.replaceRange(0..0, "")
        if (mNum.isEmpty()) return "0"
        return mNum
    }
}

fun main() {
    assertEquals("19", RemoveKDigits.removeKdigits("919", 1))
    assertEquals("1111111", RemoveKDigits.removeKdigits("1111111111", 3))
    assertEquals("11", RemoveKDigits.removeKdigits("191", 1))
    assertEquals("200", RemoveKDigits.removeKdigits("10200", 1))
    assertEquals("11", RemoveKDigits.removeKdigits("1173", 2))
    assertEquals("0", RemoveKDigits.removeKdigits("10", 1))
    assertEquals("1219", RemoveKDigits.removeKdigits("1432219", 3))

}