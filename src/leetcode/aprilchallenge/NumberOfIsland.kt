package leetcode.aprilchallenge

import java.util.*

object NumberOfIsland {
    fun numIslands(grid: Array<CharArray>): Int {
        var answer = 0

        val visited = Array(grid.size) { BooleanArray(grid[0].size) { false } }
        if (grid.isEmpty()) return 0
        val H = grid.size
        val W = grid[0].size
        val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, 1), Pair(0, -1))
        for (row in 0 until H) {
            for (col in 0 until W) {
                if (!visited[row][col] && grid[row][col] == '1') {
                    ++answer
                    val queue = LinkedList<Pair<Int, Int>>()
                    queue.add(Pair(row, col))
                    visited[row][col] = true
                    while (!queue.isEmpty()) {
                        val pair = queue.pop()


                        for (index in directions.indices) {
                            val newRow = pair.first + directions[index].first
                            val newCol = pair.second + directions[index].second

                            if (checkForBorder(
                                    newRow,
                                    newCol,
                                    H,
                                    W
                                ) && !visited[newRow][newCol] && grid[newRow][newCol] == '1'
                            ) {
                                queue.add(Pair(newRow, newCol))
                                visited[newRow][newCol] = true
                            }
                        }
                    }
                }
            }
        }

        return answer
    }

    private fun checkForBorder(i: Int, j: Int, H: Int, W: Int) = i < H && j < W && i >= 0 && j >= 0


}

fun main() {
    NumberOfIsland.numIslands(
        arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0')
        )
    ).also(::println)
    NumberOfIsland.numIslands(
        arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        )
    ).also(::println)
}