package hackerrank

import kotlin.math.max

object NonDivisibleSubset {
    fun countNonDivisible(s: IntArray, k: Int): Int {
        var sum = 0
        val countArray = IntArray(k) { 0 }
        s.forEach { value ->
            ++countArray[value % k]
        }

        for (i in 0..k / 2) {
            if (i == 0) {
                if (countArray[i] > 0) sum -= -1
            } else if (k % 2 == 0 && i == k / 2) {
                if (countArray[k / 2] > 0) sum -= -1
            } else sum += max(countArray[i], countArray[k - i])
        }
        return sum
    }
}

fun main() {
    println(NonDivisibleSubset.countNonDivisible(intArrayOf(1, 2, 6, 10), 4))
    println(NonDivisibleSubset.countNonDivisible(intArrayOf(1, 7, 2, 4), 3))
}