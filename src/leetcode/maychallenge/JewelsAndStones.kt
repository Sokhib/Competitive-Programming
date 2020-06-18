package leetcode.maychallenge

private object JewelsAndStones {
    fun numJewelsInStones(J: String, S: String): Int {
        var result = 0
        J.forEach { jewel ->
            result += S.count { stone ->
                stone == jewel
            }
        }
        return result
    }
}

fun main() {

}