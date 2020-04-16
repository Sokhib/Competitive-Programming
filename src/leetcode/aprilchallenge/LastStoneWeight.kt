package leetcode.aprilchallenge

object LastStoneWeight {
    fun lastStoneWeight(stones: IntArray): Int {
        var listOfStones = stones.toMutableList()
        while (listOfStones.count() > 1) {
            listOfStones = listOfStones.sortedDescending().toMutableList()
            listOfStones[0] = listOfStones[0] - listOfStones[1]
            listOfStones.removeAt(1)
        }
        return listOfStones.first()
    }
}

fun main() {
    LastStoneWeight.lastStoneWeight(intArrayOf(1, 2, 5, 5)).also(::println)
}