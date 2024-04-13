package leetcode.year2k24

fun romanToInt(s: String): Int {
    val romanPairs =
        hashMapOf(
            "I" to 1, "V" to 5, "X" to 10, "L" to 50, "C" to 100, "D" to 500,
            "M" to 1000, "IV" to 4, "IX" to 9, "XL" to 40, "XC" to 90,
            "CD" to 400, "CM" to 900
        )

    var result = 0
    var isAlreadyCounted = false
    for (i in 0 until s.length - 1) {
        //for skipping paired ones like XL, CM
        if (isAlreadyCounted) {
            isAlreadyCounted = false
            continue
        }

        val checkValue = s[i].toString() + s[i + 1].toString()
        if (romanPairs.containsKey(checkValue)) { // if 2 char pair
            isAlreadyCounted = true
            result += romanPairs[checkValue]!!
        } else { // single char pair
            isAlreadyCounted = false
            result += romanPairs[s[i].toString()]!!
        }
    }
    if (isAlreadyCounted.not()) { // check for the last pair to be single or not
        result += romanPairs[s[s.length - 1].toString()]!!
    }
    return result
}

fun main() {
    romanToInt("III").also(::println)
    romanToInt("LVIII").also(::println)
    romanToInt("MCMXCIV").also(::println)
}