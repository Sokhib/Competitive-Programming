package leetcode.aprilchallenge

object MaximumSubarray {
    fun maxSubArray(nums: IntArray): Int {
        var max = Integer.MIN_VALUE
        var sum = 0
        nums.forEach {
            sum += it
            sum = Math.max(sum, it)
            max = Math.max(max, sum)
        }
        return max
    }
}

fun main() {
    MaximumSubarray.maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)).also(::println)
    MaximumSubarray.maxSubArray(intArrayOf(-2, -3, -5)).also(::println)
}