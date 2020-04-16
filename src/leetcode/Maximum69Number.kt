package leetcode

/*

Given a positive integer num consisting only of digits 6 and 9.

Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

* */


class Solution1323 {
    fun maximum69Number(num: Int): Int {
        val strNum = num.toString()

        return strNum.replaceFirst('6', '9').toInt()
    }
}

fun main() {
    val sol = Solution1323()
    println(sol.maximum69Number(9669))
    println(sol.maximum69Number(9996))
    println(sol.maximum69Number(9999))
}