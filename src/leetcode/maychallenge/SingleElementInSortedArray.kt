package leetcode.maychallenge

import kotlin.test.assertEquals

private object SingleElementInSortedArray {
    fun singleNonDuplicate(nums: IntArray): Int {
        var ans = 0
        for (i in nums) {
            ans = ans xor i
        }
        return ans
    }
}

fun main() {
    assertEquals(2, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(1, 1, 2, 3, 3, 4, 4)))
    assertEquals(1, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(1, 2, 2, 3, 3, 4, 4)))
    assertEquals(4, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(1, 1, 2, 2, 3, 3, 4)))
    assertEquals(10, SingleElementInSortedArray.singleNonDuplicate(intArrayOf(3, 3, 7, 7, 10, 11, 11)))
}