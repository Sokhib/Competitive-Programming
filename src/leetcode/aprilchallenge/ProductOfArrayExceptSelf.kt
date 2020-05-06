package leetcode.aprilchallenge

object ProductOfArrayExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {
        val prefixProduct = IntArray(nums.size) { 0 }
        val suffixProduct = IntArray(nums.size) { 0 }
        val answer = IntArray(nums.size) { 0 }

        val length = nums.size
        prefixProduct[0] = nums[0]
        suffixProduct[length - 1] = nums[length - 1]
        for (i in 1 until length) {
            prefixProduct[i] = prefixProduct[i - 1] * nums[i]
        }
        for (j in length - 2 downTo 0) {
            suffixProduct[j] = nums[j] * suffixProduct[j + 1]
        }
        answer[0] = suffixProduct[1]
        answer[length - 1] = prefixProduct[length - 2]
        for (i in 1 until length - 1) {
            answer[i] = prefixProduct[i - 1] * suffixProduct[i + 1]
        }
        return answer
    }
}

fun main() {

    ProductOfArrayExceptSelf.productExceptSelf(intArrayOf(0, 1, 2, 3, 4)).forEach {
        print("$it ")
    }

}