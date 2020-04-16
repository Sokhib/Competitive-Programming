package leetcode

class Solution2 {
    fun decompressRLElist(nums: IntArray): IntArray {
        val result = arrayListOf<Int>()
        for (i in nums.indices step 2) repeat(nums[i]) {
            result.add(nums[i + 1])
        }
        return result.toIntArray()
    }
}

fun main() {
    val sol2 = Solution2()
    sol2.decompressRLElist(intArrayOf(1, 2, 3, 4)).forEach {
        print("$it ")
    }
}