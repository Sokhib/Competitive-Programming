package leetcode.may2021

fun decode(encoded: IntArray, first: Int): IntArray {
    val result = IntArray(encoded.size + 1)
    result[0] = first
    encoded.forEachIndexed { index, value ->
        result[index + 1] = result[index] xor value
    }
    return result
}

fun main() {
    decode(intArrayOf(1, 2, 3), 1).forEach { print("$it ") }
    println()
    decode(intArrayOf(6, 2, 7, 3), 4).forEach { print("$it ") }
}