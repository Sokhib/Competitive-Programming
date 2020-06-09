package leetcode.aprilchallenge

private object LongestCommonSubsequence {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array(text1.length + 1) { Array(text2.length + 1) { 0 } }

        text1.forEachIndexed { i, c1 ->
            text2.forEachIndexed { j, c2 ->
                if (c1 == c2) dp[i + 1][j + 1] = dp[i][j] + 1
                else dp[i + 1][j + 1] = dp[i + 1][j].coerceAtLeast(dp[i][j + 1])
            }
        }
        return dp[text1.length][text2.length]
    }
}

fun main() {
    LongestCommonSubsequence.longestCommonSubsequence("abc", "abcde").also(::println)
    LongestCommonSubsequence.longestCommonSubsequence("abc", "def").also(::println)
    LongestCommonSubsequence.longestCommonSubsequence("abc", "abc").also(::println)
    LongestCommonSubsequence.longestCommonSubsequence("abcdef", "abc").also(::println)
}