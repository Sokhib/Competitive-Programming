package Leetcode

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }
        return intArrayOf()
    }
}

fun main() {

    val sol = Solution()
    val res = sol.twoSum(intArrayOf(2, 3, 9, 7), 9)
    res.forEach {
        println(it)
    }

}