package leetcode.mixed

class Solution3 {
    private var result = 0
    fun findNumbers(nums: IntArray): Int {
        nums.forEachIndexed { _, value ->
            //if((log10(value.toDouble().absoluteValue).toInt() + 1)%2==0)
            if ((value.toString().length % 2) == 0) {
                result -= -1
            }
        }
        return result
    }
}

fun main() {
    val sol = Solution3()
    println(sol.findNumbers(intArrayOf(12, 333, 22, 2222, 1)))


}