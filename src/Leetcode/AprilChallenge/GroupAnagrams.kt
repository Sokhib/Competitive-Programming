package Leetcode.AprilChallenge

object GroupAnagrams {

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val listOfAnagrams: MutableList<MutableList<String>> = ArrayList()
        var doesInclude = false
        var singleSorted: String
        for (i in strs.indices) {
            singleSorted = strs[i].toCharArray().sorted().joinToString("")
            doesInclude = false
            listOfAnagrams.forEach {
                if (it[0].toCharArray().sorted().joinToString("") == singleSorted) {
                    doesInclude = true
                    it.add(strs[i])
                }
            }
            if (!doesInclude) listOfAnagrams.add(mutableListOf(strs[i]))
        }


        return listOfAnagrams
    }
}

fun main() {
    GroupAnagrams.groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")).also(::println)
}