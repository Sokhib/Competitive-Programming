package codeforces.kotlinheroes4

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val number = reader.nextInt()
    repeat(number) {
        println(reader.nextShort() + reader.nextShort())
    }
}