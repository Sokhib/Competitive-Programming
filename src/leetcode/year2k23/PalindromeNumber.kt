package leetcode.year2k23

import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

fun isPalindrome(x: Int): Boolean {
    if (x < 0) return false
    val fullLength = x.length()
    var i = 1
    val halfLength = fullLength / 2
    var tempX = x
    while (i <= halfLength) {
        val lastDigit = tempX % 10
        val firstDigit = ((x / 10.0.pow(fullLength - i.toDouble())) % 10).toInt()
        if (lastDigit != firstDigit) {
            return false
        }
        tempX /= 10
        i++
    }

    return true
}

fun Int.length() = when (this) {
    0 -> 1
    else -> log10(abs(toDouble())).toInt() + 1
}

fun isPalindromeGPT(x: Int): Boolean {
    // Handle negative numbers and numbers ending with 0
    if (x < 0 || (x % 10 == 0 && x != 0)) {
        return false
    }

    var original = x
    var reversed = 0

    while (original > reversed) {
        val digit = original % 10
        reversed = reversed * 10 + digit
        original /= 10
    }

    // For odd-length palindromes, we remove the middle digit from the reversed number
    return original == reversed || original == reversed / 10
}

fun main() {
    isPalindrome(1221).also(::println)
    isPalindrome(12121).also(::println)
    isPalindrome(12212).also(::println)

}
