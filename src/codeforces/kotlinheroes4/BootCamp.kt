package codeforces.kotlinheroes4

fun readLn() = readLine()!! // string line
fun readInt() = readLn().toInt() // single int
fun readLong() = readLn().toLong() // single long
fun readDouble() = readLn().toDouble() // single double
fun readStrings() = readLn().split(" ") // list of strings
fun readInts() = readStrings().map { it.toInt() } // list of ints
fun readLongs() = readStrings().map { it.toLong() } // list of longs
fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles

fun calculate(count: Int, k1: Int, k2: Int): Int = if (k2 >= k1 + k1) count * k1
else (count / 2) * k2 + (count % 2) * k1

fun main() {
    val times = readInt()
    repeat(times) {
        var sum = 0
        val (n, k1, k2) = readInts()
        var count = 0
        val days = readLn()

        for (c in days) {
            if (c == '0') {
                sum += calculate(count, k1, k2)
                count = 0
            } else {
                ++count
            }
        }
        sum += calculate(count, k1, k2)
        println(sum)
    }
}