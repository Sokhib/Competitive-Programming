package leetcode.mixed

class Solution1281 {
    fun subtractProductAndSum(n: Int): Int {
        var givenNumber = n
        var sum = 0
        var multiplication = 1
        while (givenNumber > 0) {
            sum += (givenNumber % 10)
            multiplication *= (givenNumber % 10)
            givenNumber /= 10
        }
        return multiplication - sum
    }
}

fun main() {
    val sol = Solution1281()

    println(sol.subtractProductAndSum(234))
    println(sol.subtractProductAndSum(4421))
}