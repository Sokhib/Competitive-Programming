package codeforces

private object Read {
    fun readLn() = readLine()!! // string line
    fun readInt() = readLn().toInt() // single int
    fun readLong() = readLn().toLong() // single long
    fun readDouble() = readLn().toDouble() // single double
    fun readStrings() = readLn().split(" ") // list of strings
    fun readInts() = readStrings().map { it.toInt() } // list of ints
    fun readLongs() = readStrings().map { it.toLong() } // list of longs
    fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles
}

private fun arpasExam(degree: Int): Int = if (degree == 0) 1
else {
    when (degree % 4) {
        1 -> 8
        2 -> 4
        3 -> 2
        0 -> 6
        else -> 0
    }
}


fun main() {
    println(arpasExam(Read.readInt()))
}