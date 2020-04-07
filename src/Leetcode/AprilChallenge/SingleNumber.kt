package Leetcode.AprilChallenge

object SolutionSingleNumber {
    fun singleNumber(nums: IntArray): Int {
        var ans = 0
        nums.groupBy { it }.values.forEach {
            if (it.size == 1) {
                ans = it[0]
            }
        }
        return ans
    }


}

fun main() {

    println(
        SolutionSingleNumber.singleNumber(intArrayOf(1, 2, 3, 2, 3))
    )
    intArrayOf(4, 1, 2, 1, 2).groupBy { it }.values.forEach {
        if (it.size == 1)
            println(it.first())
    }


}