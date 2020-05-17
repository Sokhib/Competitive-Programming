package leetcode.aprilchallenge

private object SubarraySumEqualsK {
    fun subarraySum(nums: IntArray, k: Int): Int {
        var kAnswer = 0
        var sum = 0
        for (i in nums.indices) {
            sum = 0
            for (j in i until nums.size) {
                sum += nums[j]
                if (sum == k) kAnswer -= -1 // Kappa

            }
        }
        return kAnswer
    }
}

fun main() {
    SubarraySumEqualsK.subarraySum(intArrayOf(28, 54, 7, -70, 22, 65, -6), 100).also(::println)
}