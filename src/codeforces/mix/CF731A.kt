private fun readLn() = readLine()!! // string line
private fun readInt() = readLn().toInt() // single int
private fun readLong() = readLn().toLong() // single long
private fun readDouble() = readLn().toDouble() // single double
private fun readStrings() = readLn().split(" ") // list of strings
private fun readInts() = readStrings().map { it.toInt() } // list of ints
private fun readLongs() = readStrings().map { it.toLong() } // list of longs
private fun readDoubles() = readStrings().map { it.toDouble() } // list of doubles

fun main() {
    val word = readLn()
    var count = 0
    var lastPicked = 'a'
    word.forEach { letterAt ->
        val distance = Math.abs((lastPicked - letterAt))
        count += distance.coerceAtMost(26 - distance)
        lastPicked = letterAt
    }
    println(count)
}