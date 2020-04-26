package dersler

fun main() {
    doMathOperation(1, 2) { i, j -> i + j }.also(::println)
    doMathOperation(2, 3, ::sumOfNumbers).also(::println)
    doMathOperation(2, 3, ::productOfNumbers).also(::println)

    //val division = doMathOperation(2, 3, ::divisionOfNumbers)        RETURN TYPE DOESN'T MATCH
    //val threParam = doMathOperation(2, 3, ::sumOfThreeNumbers)       NUMBER OF PARAMETERS DOESN'T MATCH

}

fun sumOfNumbers(number1: Int, number2: Int) = number1 + number2
fun productOfNumbers(number1: Int, number2: Int) = number1 * number2
fun divisionOfNumbers(number1: Int, number2: Int) = number1.toDouble() / number2
fun sumOfThreeNumbers(number1: Int, number2: Int, number3: Int) = number1 + number2 + number3

inline fun doMathOperation(number1: Int, number2: Int, function: (Int, Int) -> Int) = function(number1, number2)
