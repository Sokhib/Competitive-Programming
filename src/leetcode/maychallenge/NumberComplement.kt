package leetcode.maychallenge

private object NumberComplement {
    fun findComplement(num: Int): Int {
        var a = ""
        Integer.toBinaryString(num).forEach {
            a += if (it == '1') '0' else '1'
        }
        return Integer.parseInt(a, 2)
    }
}

fun main() {

    NumberComplement.findComplement(2).also(::println)
    NumberComplement.findComplement(5).also(::println)
    NumberComplement.findComplement(4).also(::println)
}