package codeforces.round644div3

import java.util.*
import kotlin.math.pow

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        var a1 = reader.nextInt()
        var b1 = reader.nextInt()
        if (a1 > b1) {
            val temp = a1
            a1 = b1
            b1 = temp
        }
        println(((a1 * 2).coerceAtLeast(b1)).toDouble().pow(2).toInt())
    }
}