package codeforces.kotlinheroes4

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val N = reader.nextInt()
        val K = reader.nextInt()

        val first = N / (1 + K + K * K + K * K * K)
        println("$first ${first * K} ${first * K * K} ${first * K * K * K}")
    }
}