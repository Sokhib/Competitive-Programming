package leetcode.maychallenge

import kotlin.test.assertEquals


private fun canFinish(N: Int, dislikes: Array<IntArray>): Boolean {
    val edges = HashMap<Int, ArrayList<Int>>()
    val degreeIn = IntArray(N) { 0 }
    val visited = BooleanArray(N) { false }
    fun dfs(u: Int) {
        visited[u] = true
        if (edges.containsKey(u)) {
            for (v in edges[u]!!) {
                if (--degreeIn[v] == 0) dfs(v)
            }
        }
    }

    for (e in dislikes) {
        val (a, b) = e
        if (edges.containsKey(a)) {
            edges[a]!!.add(b)
        } else {
            edges[a] = arrayListOf(b)
        }
        degreeIn[b]++
    }
    for (i in degreeIn.indices) {
        if (degreeIn[i] == 0 && !visited[i])
            dfs(i)
    }
    for (i in degreeIn) {
        if (i != 0) return false
    }
    return true
}

fun main() {
    assertEquals(true, canFinish(3, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0))))
    assertEquals(false, canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))))
    assertEquals(true, canFinish(2, arrayOf(intArrayOf(1, 0))))
}