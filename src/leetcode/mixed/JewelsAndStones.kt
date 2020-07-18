package leetcode.mixed

class Solution771 {

    fun numJewelsInStones(J: String, S: String): Int {
        var result = 0
        J.forEach { jewel ->
            result += S.count { stone ->
                stone == jewel
            }
        }
        return result
    }
}

fun main() {

    val sol = Solution771()
    println(sol.numJewelsInStones("aA", "aAAbbbb"))
    println(sol.numJewelsInStones("z", "ZZ"))
}