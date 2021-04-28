package leetcode.april2021

fun shuffle(nums: IntArray, n: Int): IntArray {
    val result = arrayListOf<Int>()
    for (i in 0 until n) {
        result.add(nums[i])
        result.add(nums[i + n])
    }
    return result.toIntArray()
}

fun main() {
    shuffle(intArrayOf(2, 5, 1, 3, 4, 7), 3).forEach { print({ "$it " }) }
}