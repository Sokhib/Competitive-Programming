package leetcode.may2021

fun minPartitions(n: String): Int {
    var max = '0'
    n.forEach {
        if (it > max) max = it
    }
    return max.code - 48
}

fun main() {
    minPartitions("932").also(::println)
}