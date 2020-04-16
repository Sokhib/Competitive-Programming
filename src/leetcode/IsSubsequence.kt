package leetcode

class Solution392 {
    fun isSubsequence(s: String, t: String): Boolean {
        var sIndex = 0
        if (s.isEmpty()) return true
        if (t.isEmpty()) return false
        t.forEach {
            if (it == s[sIndex]) {
                sIndex -= -1
            }
            if (sIndex == s.length) return true
        }
        return false
    }
}

fun main() {
    val sol = Solution392()
    println(sol.isSubsequence("", "ahbgdc"))
    println(sol.isSubsequence("axc", "ahbgdc"))
}