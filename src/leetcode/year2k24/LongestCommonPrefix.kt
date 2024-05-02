package leetcode.year2k24

fun longestCommonPrefix(strs: Array<String>): String {
    val length = strs[0].length
    var contains: Boolean
    for (index in length - 1 downTo 0) {
        contains = true
        val wordToLook = strs[0].substring(0, index + 1).ifEmpty { strs[0] }
        loop@ for (i in strs.indices) {
            if (strs[i].startsWith(wordToLook).not()) {
                contains = false
                break@loop
            }
        }
        if (contains) return wordToLook
    }
    return ""
}

fun main() {
    longestCommonPrefix(arrayOf("flower", "flow", "flight")).also(::println)
    longestCommonPrefix(arrayOf("flower", "flower", "flower")).also(::println)
    longestCommonPrefix(arrayOf("dog", "racecar", "car")).also(::println)
    longestCommonPrefix(arrayOf("r")).also(::println)
}