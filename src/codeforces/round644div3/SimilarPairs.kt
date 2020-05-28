package codeforces.round644div3

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val numberOfPairs = reader.nextInt()
        val pairs = Array(numberOfPairs) { 0 }
        repeat(numberOfPairs) { index ->
            pairs[index] = reader.nextInt()
        }
        pairs.sort()
        var even = pairs.filter { it % 2 == 0 }.count() % 2 == 0
        if (!even) {
            for (i in pairs.indices) {
                if (pairs[i] % 2 == 0) {
                    if ((pairs.contains(pairs[i] - 1) || pairs.contains(pairs[i] + 1)))
                        even = true

                }
            }
        }
        if (even) println("YES") else println("NO")

    }
}