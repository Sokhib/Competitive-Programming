package codeforces.kotlinheroes4

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val sides = arrayOf(reader.nextInt(), reader.nextInt(), reader.nextInt(), reader.nextInt())
        sides.sort()
        if (sides[0] + sides[1] == sides[2] && sides[2] == sides[3]) println("yes")
        else println("no")
    }
}