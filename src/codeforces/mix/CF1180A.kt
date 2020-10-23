package codeforces.mix

import java.util.*
import kotlin.math.pow

fun main() {
    val reader = Scanner(System.`in`)
    val nThOrderRhomb = reader.nextFloat()
    println((nThOrderRhomb.pow(2) + (nThOrderRhomb - 1).pow(2)).toInt())
}