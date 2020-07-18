package leetcode.mixed

class Solution1221 {
    fun balancedStringSplit(s: String): Int {
        var count = 0
        var balanced = 0
        for (i in s.indices) {
            balanced -= if (s[i] == 'R') -1
            else 1
            if (balanced == 0) count -= -1
        }
        return count
    }
}

fun main() {
    val sol1221 = Solution1221()
//    println(sol1221.balancedStringSplit("RRLLRL"))
//    println(sol1221.balancedStringSplit("RLRRLLRR"))
//    println(sol1221.balancedStringSplit("RLRRLLLL"))
    println(sol1221.balancedStringSplit("RLRRLLRLLR"))
    println(sol1221.balancedStringSplit("RRRLLR"))
    println(sol1221.balancedStringSplit("RLRRRLLRLL"))
}