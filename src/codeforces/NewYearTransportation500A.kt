package codeforces

import java.util.*

private object Input {
    fun readLn() = readLine()!! // string line
    fun readInt() = readLn().toInt() // single int
    fun readLong() = readLn().toLong() // single long
    fun readDouble() = readLn().toDouble() // single double
    fun readStrings() = readLn().split(" ") // list of strings
    fun readInts() = readStrings().map { it.toInt() } // list of ints
    fun readLongs() = readStrings().map { it.toLong() } // list of longs
    fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles
}

fun main() {
    val (n, t) = Input.readInts()
    val cells = Input.readInts()

    val queue = LinkedList<Int>()
    queue.add(0)
    while (!queue.isEmpty() && queue.first < t) {
        val element = queue.pop()
        if (element == t - 1) {
            println("YES")
            return
        }
        queue.add(element + cells[element])
    }
    println("NO")
}