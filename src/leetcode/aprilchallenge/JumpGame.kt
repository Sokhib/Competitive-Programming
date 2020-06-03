package leetcode.aprilchallenge

private object JumpGame {
    fun canJump(nums: IntArray): Boolean {
        if (nums.size == 1) return true
        if (nums[0] == 0) return false
        val visitedPlaces = IntArray(nums.size) { 0 }
        for (i in 0 until nums.size - 1) {
            if (nums[i] == 0 && visitedPlaces[i + 1] == 0) return false
            var j = i
            while (j <= i + nums[i] && j < nums.size) {
                visitedPlaces[j] = 1
                ++j
            }
            if (visitedPlaces[nums.size - 1] == 1) return true
        }
        if (visitedPlaces.contains(0)) return false
        return true
    }
}

fun main() {
    JumpGame.canJump(intArrayOf(2, 3, 1, 1, 4)).also(::println)
    JumpGame.canJump(intArrayOf(3, 2, 1, 0, 4)).also(::println)
    JumpGame.canJump(intArrayOf(1, 0, 1, 0)).also(::println)
    JumpGame.canJump(intArrayOf(2, 0, 0)).also(::println)
}