//package codeforces.kotlinheroes4

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val number = reader.nextInt().toString()
        val size = number.length
        val nums = MutableList(0) { "" }
        var num = ""
        for (i in number.indices) {
            if (number[i] != '0') {
                num = ""
                num += number[i]
                repeat(size - i - 1) {
                    num += '0'
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