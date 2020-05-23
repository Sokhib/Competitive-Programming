package codeforces.kotlinheroes4

import java.util.*


fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()

    repeat(times) {
        var temp = 0
        var alice = 0
        var bob = 0
        var aliceLastMove = 0
        var bobLastMove = 0
        var numberOfMoves = 0
        val N = reader.nextInt()
        var candies = MutableList(0) { 0 }
        repeat(N) { candies.add(reader.nextInt()) }
        var whosMove = 1
        while (candies.isNotEmpty()) {
            when {
                whosMove == 1 -> {
                    ++numberOfMoves
                    alice += candies[0]
                    aliceLastMove = alice
                    candies = if (candies.size == 1) {
                        emptyList<Int>().toMutableList()
                    } else candies.drop(1) as MutableList<Int>
                }
                whosMove % 2 == 0 -> {
                    //bob's move
                    while (candies.isNotEmpty() && temp <= aliceLastMove) {
                        temp += candies.last()
                        candies =
                            if (candies.size == 1) {
                                bob += temp
                                ++numberOfMoves
                                temp = 0
                                emptyList<Int>().toMutableList()
                            } else candies.dropLast(1) as MutableList<Int>
                    }
                    if (temp > aliceLastMove) {
                        bobLastMove = temp
                        bob += temp
                        ++numberOfMoves
                        temp = 0
                    }
                }
                else -> {
                    //alice's move
                    while (candies.isNotEmpty() && temp <= bobLastMove) {
                        temp += candies.first()
                        candies =
                            if (candies.size == 1) {
                                alice += temp
                                ++numberOfMoves
                                temp = 0
                                emptyList<Int>().toMutableList()
                            } else candies.drop(1) as MutableList<Int>
                    }
                    if (temp > bobLastMove) {
                        aliceLastMove = temp
                        alice += temp
                        ++numberOfMoves
                        temp = 0
                    }
                }
            }
            ++whosMove
        }
        println("$numberOfMoves $alice $bob")
    }
}