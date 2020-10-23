package leetcode.junechallenge

private fun reverseString(s: CharArray): Unit {
    for (index in 0 until s.size / 2) {
        s[s.size - index - 1] = s[index].also { s[index] = s[s.size - index - 1] }
    }
    print(s)
}