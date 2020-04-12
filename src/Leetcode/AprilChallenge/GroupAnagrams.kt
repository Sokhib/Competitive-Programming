package Leetcode.AprilChallenge

object GroupAnagrams {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val listOfAnagrams = HashMap<String, MutableList<String>>()
        var sortedStr = ""
        for (i in strs.indices) {
            sortedStr = strs[i].toCharArray().sorted().joinToString("")
            if (listOfAnagrams[sortedStr] != null)
                listOfAnagrams[sortedStr]!!.add(strs[i])
            else listOfAnagrams[sortedStr] = mutableListOf(strs[i])
        }
        return listOfAnagrams.values.toList()
    }
}

fun main() {
    GroupAnagrams.groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")).also(::println)
}