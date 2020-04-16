package codeforces

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val number = reader.nextLine()

    number.forEach {
        if (it.toString().toInt() % 8 == 0) {
            println("YES")
            println(it)
            return
        }
    }



    for (i in 0 until number.length - 1) {
        for (j in i + 1 until number.length) {
            if ((number[i].toInteger() * 10 + number[j].toInteger()) % 8 == 0) {
                println("YES")
                println(number[i].toInteger() * 10 + number[j].toInteger())
                return
            }
        }
    }
    for (i in 0 until number.length - 2) {
        for (j in i + 1 until number.length - 1) {
            for (k in j + 1 until number.length) {
                if ((number[i].toInteger() * 100 + number[j].toInteger() * 10 + number[k].toInteger()) % 8 == 0) {
                    println("YES")
                    println(number[i].toInteger() * 100 + number[j].toInteger() * 10 + number[k].toInteger())
                    return
                }
            }
        }
    }
    println("NO")


}

fun Char.toInteger(): Int {
    return this.toString().toInt()
}
