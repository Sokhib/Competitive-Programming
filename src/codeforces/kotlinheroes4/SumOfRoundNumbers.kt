//package codeforces.kotlinheroes4

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val numberInput = reader.nextInt().toString()
        val nums = MutableList(0) { "" }
        var num: String
        for (i in numberInput.indices) {
            if (numberInput[i] != '0') {
                num = numberInput.substring(i)
                for (j in 1 until num.length) {
                    num = num.replaceRange(j, j + 1, "0")
                }
                nums.add(num)
            }

        }
        println("${nums.size}")
        nums.forEach {
            println(it)
        }
    }
}