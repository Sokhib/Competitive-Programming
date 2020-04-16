package leetcode

class Solution189 {
    fun rotate(nums: IntArray, k: Int) {
        if (nums.isEmpty() || nums.size == 1 || k < 1) {
            return
        }
        val newFreakingK = k % nums.size
        val doubledNums = nums + nums
        val result = doubledNums.sliceArray((nums.size - newFreakingK) until (doubledNums.size - newFreakingK))

        result.forEachIndexed { index, value ->
            nums[index] = value
        }
    }
}

fun main() {

    val solution189 = Solution189()
    solution189.rotate(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3)
//    println()
//    solution189.rotate(intArrayOf(-1, -100, 3, 99), 2)

}