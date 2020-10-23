package codeforces.mix


private fun readLn() = readLine()!! // string line
private fun readInt() = readLn().toInt() // single int
private fun readLong() = readLn().toLong() // single long
private fun readDouble() = readLn().toDouble() // single double
private fun readStrings() = readLn().split(" ") // list of strings
private fun readInts() = readStrings().map { it.toInt() } // list of ints
private fun readLongs() = readStrings().map { it.toLong() } // list of longs
private fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles


fun main() {
    readInt()
    val card = readLn()
    val zeros = card.count {
        it == 'z'
    }
    val ones = card.count {
        it == 'n'
    }
    for (i in 0 until ones) {
        print("${1} ")
    }
    for (i in 0 until zeros) {
        print("${0} ")
    }
}