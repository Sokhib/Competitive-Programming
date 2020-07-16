package leetcode.maychallenge

import kotlin.test.assertEquals


private fun possibleBipartition(N: Int, dislikes: Array<IntArray>): Boolean {
    val edges = HashMap<Int, ArrayList<Int>>()
    var ok = true
    val colors = IntArray(N + 1) { -1 }
    fun dfs(u: Int, color: Int) {
        for (v in edges[u]!!) {
            if (colors[v] == -1) {
                colors[v] = color xor 1
                dfs(v, colors[v])
            } else if (colors[v] == color) {
                ok = false
            }
        }
    }

    for (e in dislikes) {
        if (edges.containsKey(e[0])) {
            edges[e[0]]!!.add(e[1])
        } else {
            edges[e[0]] = arrayListOf(e[1])
        }
        if (edges.containsKey(e[1])) {
            edges[e[1]]!!.add(e[0])
        } else {
            edges[e[1]] = arrayListOf(e[0])
        }
    }
    for (vertex in edges.keys) {
        if (colors[vertex] == -1) {
            colors[vertex] = 0
            dfs(vertex, colors[vertex])
        }
    }
    return ok
}

fun main() {
    assertEquals(true, possibleBipartition(4, arrayOf(intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(2, 4))))
    assertEquals(
        false,
        possibleBipartition(
            5,
            arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(1, 5))
        )
    )
}