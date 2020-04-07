package Leetcode.AprilChallenge

//XOR returns false for 1, 1 and 0, 0

object SolutionSingleNumber {
    fun singleNumber(nums: IntArray): Int {
        var result = 0
        nums.forEach {
            result = result xor it
        }
        return result
    }


}

fun main() {

    println(SolutionSingleNumber.singleNumber(intArrayOf(1, 2, 3, 2, 3)))
    println(SolutionSingleNumber.singleNumber(intArrayOf(4, 4, 2, 2, 5)))

}