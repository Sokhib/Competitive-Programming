package leetcode.may2021

fun numberOfSteps(num: Int): Int {
    var temp = num
    var result = 0
    while (temp > 0) {
        temp = if (temp % 2 == 0) temp / 2 else temp - 1
        result += 1
    }
    return result
}

fun main() {
    numberOfSteps(14).also(::println)
    numberOfSteps(123).also(::println)
    numberOfSteps(8).also(::println)
}