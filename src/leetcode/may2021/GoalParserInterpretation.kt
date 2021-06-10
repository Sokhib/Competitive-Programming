package leetcode.may2021

fun interpret(command: String): String {
    var res = command
    while (res.contains("()")) {
        res = res.replace("()", "o")
    }
    while (res.contains("(al)")) {
        res = res.replace("(al)", "al")
    }
    return res
}

fun main() {
    interpret("G()()()()(al)").also(::println)
}