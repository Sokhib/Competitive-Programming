package Leetcode

class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val hashMap = HashMap<Int, Int>()

        nums.forEachIndexed { index, value ->
            if (hashMap.containsKey(target - value)) {
                return intArrayOf(hashMap[target - value]!!, index)
            }
            hashMap[value] = index
        }
        return intArrayOf()
    }
}

fun main() {

    val sol = Solution()
    val res = sol.twoSum(intArrayOf(2, 7, 9, 2), 9)
    res.forEach {
        println(it)
    }

}