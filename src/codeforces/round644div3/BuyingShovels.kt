//package codeforces.round644div3

import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val times = reader.nextInt()
    repeat(times) {
        val N = reader.nextInt()
        val K = reader.nextInt()
        var ans = N

        var i = 1
        while (i * i <= N) {
            if (N % i == 0) {
                if (i <= K) ans = ans.coerceAtMost(N / i)
                if (N / i <= K) {
                    ans = ans.coerceAtMost(i)
                }
            }
            ++i

        }
        println(ans)
    }

}