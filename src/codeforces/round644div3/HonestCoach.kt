package codeforces.round644div3

import java.util.*
import kotlin.math.min

fun main() {

    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val numberOfAthletes = reader.nextInt()
        val athletes = Array(numberOfAthletes) { 0 }
        repeat(numberOfAthletes) { index ->
            athletes[index] = reader.nextInt()
        }
        athletes.sort()
        var minDiff = 1001
        for (i in 0 until numberOfAthletes - 1) {
            minDiff = min(minDiff, (athletes[i + 1] - athletes[i]))
        }
        println(minDiff)
    }
}