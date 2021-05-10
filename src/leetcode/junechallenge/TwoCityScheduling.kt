package leetcode.junechallenge

private fun twoCitySchedCost(costs: Array<IntArray>): Int {
    var sum = 0
    costs.forEach {
        sum += (it.minOrNull()!!)
    }
    return sum
}


private fun solution(A: String, B: String): Int {
    val alphabet = HashMap<Char, Int>(26)
    var result = 0
    A.forEach {
        if (alphabet[it] == null) {
            alphabet[it] = 1
        } else alphabet[it] = alphabet[it]!! + 1
    }
    B.forEach {
        if (alphabet[it] == null) {
            alphabet[it] = -1
        } else alphabet[it] = alphabet[it]!! - 1
    }
    alphabet.forEach { (_, i) ->
        result += Math.abs(i)
    }
    return result
}

fun solution321(N: Int): Int {
    val first = 0
    val second = 1
    val sum = first + second
    for (i in 2 until N) {

    }
    return 1
}


private fun solution2(A: IntArray) {
    var sum = 0.0
    A.forEachIndexed { index, i ->
        sum += (i * Math.pow((-2.0), index.toDouble()))
    }
    sum = Math.ceil(sum / 2)
    val intSum = sum.toInt()

    println(intSum.toString(radix = 2))
}

fun main() {
    println(5 shl 1)
//    println(solution2(intArrayOf(1, 0, 0, 1, 1, 1)))
//    println((-23).toString(radix = 2))
//    println(solution("BA", "BBA"))
//    println(solution("rather", "harder"))
//    assertEquals(
//        110,
//        twoCitySchedCost(arrayOf(intArrayOf(10, 20), intArrayOf(30, 200), intArrayOf(400, 50), intArrayOf(30, 20)))
//    )
//    //[[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
//    assertEquals(
//        1859,
//        twoCitySchedCost(arrayOf(intArrayOf(10, 20), intArrayOf(30, 200), intArrayOf(400, 50), intArrayOf(30, 20)))
//    )
}