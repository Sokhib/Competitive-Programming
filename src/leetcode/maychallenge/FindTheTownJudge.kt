package leetcode.maychallenge

import kotlin.test.assertEquals

private object FindTheTownJudge {
    fun findJudge(N: Int, trust: Array<IntArray>): Int {
        if (trust.isEmpty()) {
            return if (N == 1) 1 else -1
        }
        val judges = IntArray(N + 1) { 0 }
        var realJudge = -1
        for (person in trust) {
            judges[person[1]] -= -1
        }
        for (i in judges.indices) {
            if (judges[i] == N - 1) realJudge = i
        }
        trust.forEach {
            if (it[0] == realJudge) return -1
        }
        return realJudge
    }
}

fun main() {

    assertEquals(
        3, FindTheTownJudge.findJudge(
            4, arrayOf(
                intArrayOf(1, 3),
                intArrayOf(1, 4),
                intArrayOf(2, 3),
                intArrayOf(2, 4),
                intArrayOf(4, 3)
            )
        )
    )
    assertEquals(2, FindTheTownJudge.findJudge(2, arrayOf(intArrayOf(1, 2))))

}