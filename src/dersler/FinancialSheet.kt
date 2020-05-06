package dersler

import kotlin.math.pow

fun main() {
    valueOfNowLater(100, .2).also(::println)
    valueOfLaterNow(100, .2).also(::println)


}

// Simdiki 100 liranin N gunden, aydan, yildan sonraki degeri ne kadar? percentageInPeriod is BANKA FAIZI according to gun, ay, yil
fun valueOfNowLater(amount: Int, percentageInPeriod: Double, period: Int = 1): Double =
    amount * (1 + percentageInPeriod).pow(period)

// N gun, ay, yil sonraki 100 liranin degeri simdi ne kadar?
fun valueOfLaterNow(amount: Int, percentageInPeriod: Double, period: Int = 1): Double =
    amount.toDouble() / (1 + percentageInPeriod).pow(period)
