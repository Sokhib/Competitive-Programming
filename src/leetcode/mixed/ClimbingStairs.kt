package leetcode.mixed

object Solution70 {
    fun climbStairs(n: Int) = fibonacci(n)

    private fun fibonacci(n: Int): Int {
        var first = 0
        var second = 1
        var sum = 0
        for (i in 1..n) {
            sum = first + second
            first = second
            second = sum
        }
        return sum
    }
}

fun main() {
    println(Solution70.climbStairs(2))
    println(Solution70.climbStairs(3))
    println(Solution70.climbStairs(4))
    println(Solution70.climbStairs(5))

}
