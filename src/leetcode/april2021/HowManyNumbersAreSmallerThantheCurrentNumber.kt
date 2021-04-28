package leetcode.april2021

fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
    val numbers = hashMapOf<Int, Int>()
    nums.forEachIndexed { index, value ->
        numbers[index] = value
    }
    val sorted = numbers.toList().sortedBy { (_, value) -> value }.toMap()
    val resultingArray = IntArray(nums.size)
    var index = 0
    var prevValue = 0
    var sameNumCont = 0
    var howMany = 0
    sorted.forEach { pair ->
        if (index == 0) {
            ++index
            prevValue = pair.value
            resultingArray[pair.key] = howMany
        } else {
            if (prevValue == pair.value) {
                resultingArray[pair.key] = howMany
                ++sameNumCont
            } else {
                prevValue = pair.value
                howMany += sameNumCont
                ++howMany
                sameNumCont = 0
                resultingArray[pair.key] = howMany
            }
        }
    }
    return resultingArray
}

fun main() {

    smallerNumbersThanCurrent(intArrayOf(8, 1, 2, 2, 3)).forEach { print("$it ") }
    println()
    smallerNumbersThanCurrent(intArrayOf(1, 1, 7)).forEach { print("$it ") }
    println()
    smallerNumbersThanCurrent(intArrayOf(8, 1, 2, 2, 3)).forEach { print("$it ") }
    println()
    smallerNumbersThanCurrent(intArrayOf(6, 5, 4, 8)).forEach { print("$it ") }

}