package leetcode.april2021

fun numIdenticalPairs(nums: IntArray): Int {
    val pairs = hashMapOf<Int, Int>()
    nums.forEach {
        pairs[it] = if (pairs[it] == null) 1 else pairs[it]!! + 1
    }
    var ans = 0
    pairs.forEach {
        val value = it.value.toFloat()
        ans += (value * ((value - 1) / 2)).toInt()
    }
    return ans
}

fun main() {
    println(numIdenticalPairs(intArrayOf(1, 2, 3, 1, 1, 3)))
    println(numIdenticalPairs(intArrayOf(1, 1, 1, 1)))
    println(numIdenticalPairs(intArrayOf(1, 2, 3)))
}