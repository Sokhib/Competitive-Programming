fun main() {
    val Q = 6
    val a = intArrayOf(2, 3)
    val dp = IntArray(40) { 0 }
    dp[0] = 1
    for (i in 0 until Q) {
        for (x in a)
            dp[i + x] += dp[i]
    }
    println(dp[Q])
    dp.forEach { print("$it ") }
}