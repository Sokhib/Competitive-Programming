package leetcode.maychallenge

import kotlin.test.assertEquals

private object SingleElementInSortedArray {
    fun singleNonDuplicate(nums: IntArray): Int {
        val size = nums.size
        if (size == 1) return nums[0]
        if (nums.isEmpty()) return 0
        if (nums[0] != nums[1]) return nums[0]
        if (nums[size - 1] != nums[size - 2]) return nums[size - 1]
        for (i in 0 until size - 2 step 2) {
            if (nums[i] != nums[i + 1]) return nums[i]
        }
        return 0
    }
}

fun main() {
    assertEquals(2, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(1, 1, 2, 3, 3, 4, 4)))
    assertEquals(1, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(1, 2, 2, 3, 3, 4, 4)))
    assertEquals(4, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(1, 1, 2, 2, 3, 3, 4)))
    assertEquals(10, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(3, 3, 7, 7, 10, 11, 11)))
}