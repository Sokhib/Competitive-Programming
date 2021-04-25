package leetcode.april2021

fun runningSum(nums: IntArray): IntArray {
    for (i in 1 until nums.size) {
        nums[i] = nums[i - 1] + nums[i]
    }
    return nums
}

fun main() {
    runningSum(intArrayOf(1, 2, 3, 4)).forEach { print("$it ") }
//    assertEquals(intArrayOf(1,3,6,10), runningSum(intArrayOf(1,2,3,4)))
}
