package leetcode.maychallenge

import kotlin.test.assertEquals

private object RemoveKDigits {
    fun removeKdigits(num: String, k: Int): String {
        var kk = k
        var mNum = num
        while (kk > 0) {
            if (mNum.length <= 1) return "0"
            for (i in 0 until mNum.length - 1) {
                if (mNum[i] > mNum[i + 1]) {
                    mNum = mNum.replaceRange(i..i, "")
                    --kk
                    break
                }
                if (i == mNum.length - 2) {
                    mNum = mNum.replaceRange(mNum.length - 1 until mNum.length, "")
                    --kk
                }
            }
            while (mNum.isNotEmpty() && mNum[0] == '0')
                mNum = mNum.replaceRange(0..0, "")
        }

        if (mNum.isEmpty()) return "0"
        return mNum
    }
}

fun main() {
    assertEquals("11", RemoveKDigits.removeKdigits("112", 1))
    assertEquals("0", RemoveKDigits.removeKdigits("9", 1))
    assertEquals("0", RemoveKDigits.removeKdigits("10", 2))
    assertEquals("11", RemoveKDigits.removeKdigits("1173", 2))
    assertEquals("11", RemoveKDigits.removeKdigits("191", 1))
    assertEquals("19", RemoveKDigits.removeKdigits("919", 1))
    assertEquals("1321", RemoveKDigits.removeKdigits("43214321", 4))
    assertEquals("200", RemoveKDigits.removeKdigits("10200", 1))
    assertEquals("0", RemoveKDigits.removeKdigits("10", 1))
    assertEquals("1219", RemoveKDigits.removeKdigits("1432219", 3))
    assertEquals("1111111", RemoveKDigits.removeKdigits("1111111111", 3))

}