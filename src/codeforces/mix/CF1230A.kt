package codeforces.mix

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val candies = IntArray(4) { 0 }
    for (i in 0 until 4)
        candies[i] = reader.nextInt()
    candies.sort()
    when {
        candies[0] + candies[3] == candies[1] + candies[2] ||
                candies[0] + candies[1] + candies[2] == candies[3] -> println("YES")
        else -> println("NO")
    }
}

//val date = Date(1578855901L * 1000)