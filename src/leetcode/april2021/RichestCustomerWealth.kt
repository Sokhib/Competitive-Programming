package leetcode.april2021


fun maximumWealth(accounts: Array<IntArray>): Int = accounts.maxByOrNull { account -> account.sum() }!!.sum()

fun main() {
    println(maximumWealth(arrayOf(intArrayOf(1, 5), intArrayOf(7, 3), intArrayOf(3, 5))))
}