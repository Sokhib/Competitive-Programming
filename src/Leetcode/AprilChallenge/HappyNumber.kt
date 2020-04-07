package Leetcode.AprilChallenge
// While number = 1 or found repeated number we can return how many repetitions were made.
//This way we can replace while(true)
object HappyNumber {
    fun isHappy(n: Int): Boolean {
        val visited = HashSet<Int>()
        var number = n
        while (true) {
            if (number == 1) return true
            number = sumOfSquaresOfDigits(number)
            if (visited.contains(number)) return false
            visited.add(number)
        }
    }

    private fun sumOfSquaresOfDigits(n: Int): Int {
        var number = n
        var sum = 0
        while (number > 0) {
            val digit = number % 10
            number /= 10
            sum += (digit * digit)
        }
        return sum
    }
}

fun main() {
    println(HappyNumber.isHappy(19))
}