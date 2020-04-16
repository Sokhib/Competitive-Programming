package leetcode


class Solution1266 {
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
        var sum = 0
        for (i in 0 until (points.size - 1)) {
            sum += Math.max(
                (Math.abs(points[i + 1][0] - points[i][0])),
                (Math.abs(points[i + 1][1] - points[i][1]))
            )
        }
        return sum
    }
}

fun main() {
    val sol = Solution1266()
    println(sol.minTimeToVisitAllPoints(arrayOf(intArrayOf(1, 1), intArrayOf(3, 4), intArrayOf(-1, 0))))
    println(sol.minTimeToVisitAllPoints(arrayOf(intArrayOf(3, 2), intArrayOf(-2, 2))))
}