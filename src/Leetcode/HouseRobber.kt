package Leetcode

/*
* You are a professional robber planning to rob houses along a street.
* Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
* adjacent houses have security system connected and it will automatically contact the police
* if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
*  determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*
* */



object Solution198 {
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        if (nums.isEmpty()) return 0
        val dp = IntArray(nums.size) { 0 }
        dp[0] = nums[0]
        dp[1] = nums[1]
        for (i in 2 until nums.size) {
            dp[i] = dp.sliceArray(0..(i - 2)).max()!! + nums[i]
        }
        return dp.max()!!
    }
}

fun main() {
    println(Solution198.rob(intArrayOf(1, 2, 3, 1)))
    println(Solution198.rob(intArrayOf(1, 2, 3)))
    println(Solution198.rob(intArrayOf(1, 2)))
    println(Solution198.rob(intArrayOf(0, 0)))
    println(Solution198.rob(intArrayOf(2)))
    println(Solution198.rob(intArrayOf(2, 7, 9, 3, 1)))
    println(Solution198.rob(intArrayOf(5, 6, 5, 7, 9, 5)))
    println(Solution198.rob(intArrayOf(6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3)))
}