//package codeforces.round644div3

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val numberOfPairs = reader.nextInt()
        val pairs = Array(numberOfPairs) { 0 }
        var ones = 0
        var zeros = 0
        repeat(numberOfPairs) { index ->
            pairs[index] = reader.nextInt()
            if (pairs[index] % 2 == 0) {
                ++zeros
            } else {
                ++ones
            }
        }
        pairs.sort()
        if (zeros % 2 == 0 && ones % 2 == 0) println("YES")
        else if (pairs.size == 2) {
            if (pairs[0] == pairs[1] - 1) println("YES") else println("NO")
        } else if (zeros > ones || ones > zeros || ones == zeros) {
            for (i in 0 until pairs.size - 1) {
                if (pairs[i] == pairs[i + 1] - 1) {
                    --ones
                    --zeros
                }
            }
            if (ones % 2 == 0 && zeros % 2 == 0) println("YES") else println("NO")
        } else println("NO")

    }
}