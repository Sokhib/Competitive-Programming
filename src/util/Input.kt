package util

object Input {
    fun readLn() = readLine()!! // string line
    fun readInt() = readLn().toInt() // single int
    fun readLong() = readLn().toLong() // single long
    fun readDouble() = readLn().toDouble() // single double
    fun readStrings() = readLn().split(" ") // list of strings
    fun readInts() = readStrings().map { it.toInt() } // list of ints
    fun readLongs() = readStrings().map { it.toLong() } // list of longs
    fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles
}