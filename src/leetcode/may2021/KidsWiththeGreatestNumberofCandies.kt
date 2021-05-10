package leetcode.may2021

fun kidsWithCandies(candies: IntArray, extraCandies: Int): BooleanArray {
    val result = BooleanArray(candies.size) { false }
    val maxCandy: Int = candies.maxOrNull()!!
    candies.forEachIndexed { index, candy ->
        result[index] = candy + extraCandies >= maxCandy
    }
    return result
}

fun main() {

}