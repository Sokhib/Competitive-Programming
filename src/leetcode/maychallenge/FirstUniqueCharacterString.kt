package leetcode.maychallenge

private object FirstUniqueCharacterString {
    fun firstUniqChar(s: String): Int {
        val map = HashMap<Char, Int>(s.length)
        s.forEach {
            if (map[it] == null) {
                map[it] = 1
            } else map[it] = map[it]!! + 1
        }
        s.forEachIndexed { index, c ->
            if (map[c] == 1) return index
        }
        return -1
    }
}

fun main() {
    FirstUniqueCharacterString.firstUniqChar("cc").also(::println)
    FirstUniqueCharacterString.firstUniqChar("leetcode").also(::println)
    FirstUniqueCharacterString.firstUniqChar("loveleetcode").also(::println)

}