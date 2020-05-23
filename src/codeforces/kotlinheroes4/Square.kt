package codeforces.kotlinheroes4

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        var a1 = reader.nextInt()
        var b1 = reader.nextInt()
        var temp = 0
        if (a1 > b1) {
            temp = a1
            a1 = b1
            b1 = temp
        }
        var a2 = reader.nextInt()
        var b2 = reader.nextInt()
        if (a2 > b2) {
            temp = a2
            a2 = b2
            b2 = temp
        }

        if (a1 + a2 == b1 && b1 == b2) println("yes")
        else println("no")
    }
}