package leetcode.maychallenge

import kotlin.test.assertEquals

private fun possibleBipartition(N: Int, dislikes: Array<IntArray>): Boolean {
    if (N <= 2) return true
    dislikes.sortBy { it.first() }
    val group1 = HashMap<Int, Boolean>()
    val group2 = HashMap<Int, Boolean>()
    group1[dislikes[0][0]] = true
    group2[dislikes[0][1]] = true
    for (i in 1 until dislikes.size) {
        if (group1.containsKey(dislikes[i][0]) && group1.containsKey(dislikes[i][1])) return false
        if (group2.containsKey(dislikes[i][0]) && group2.containsKey(dislikes[i][1])) return false

        if (group1.containsKey(dislikes[i][0]) && !group1.containsKey(dislikes[i][1]))
            group2[dislikes[i][1]] = true
        else if (group2.containsKey(dislikes[i][0]) && !group2.containsKey(dislikes[i][1]))
            group1[dislikes[i][1]] = true
        else if (!group1.containsKey(dislikes[i][0]) && !group2.containsKey(dislikes[i][1])) {
            group1[dislikes[i][0]] = true
            group2[dislikes[i][1]] = true
        }
    }
    return true
}

fun main() {
    assertEquals(
        false,
        possibleBipartition(
            5,
            arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(1, 5))
        )
    )
    assertEquals(true, possibleBipartition(4, arrayOf(intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(2, 4))))
}