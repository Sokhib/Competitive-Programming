package leetcode.may2021

fun restoreString(s: String, indices: IntArray): String {
    val result = CharArray(s.length)
    indices.forEach { index ->
        result[indices[index]] = s[index]
    }

    return String(result)
}

fun main() {
    println(restoreString("codeleet", intArrayOf(4, 5, 6, 7, 0, 2, 1, 3)))
}