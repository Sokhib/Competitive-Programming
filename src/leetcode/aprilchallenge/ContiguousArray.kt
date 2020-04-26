package leetcode.aprilchallenge

object ContiguousArray {
    fun findMaxLength(nums: IntArray): Int {
        val mapOfIndexes = mutableMapOf<Int, MutableList<Int>>()
        var maxSub = 0
        val prefSum = IntArray(nums.size + 1)
        mapOfIndexes[0] = mutableListOf(0)
        prefSum[0] = 0
        for (i in nums.indices) {
            prefSum[i + 1] = if (nums[i] == 0) prefSum[i] - 1
            else prefSum[i] + 1
            if (mapOfIndexes[prefSum[i + 1]].isNullOrEmpty()) mapOfIndexes[prefSum[i + 1]] = mutableListOf(i + 1)
            else mapOfIndexes[prefSum[i + 1]]!!.add(i + 1)
        }

        mapOfIndexes.forEach { (_, listOfIndexes) ->
            maxSub = maxSub.coerceAtLeast(listOfIndexes.last() - listOfIndexes.first())
        }
//        println()
//        prefSum.forEach {
//            print("$it ")
//        }
//        println()
        return maxSub
    }
}

fun main() {
    ContiguousArray.findMaxLength(intArrayOf(0, 1, 1, 0, 1)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(1, 1, 1, 0, 1, 0, 1, 0)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(0, 0, 1, 1)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(0, 1)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(1, 1, 0, 0, 1, 1, 0, 0)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(0, 1, 1)).also(::println)
    ContiguousArray.findMaxLength(intArrayOf(0, 0, 1, 0, 0, 0, 1, 1)).also(::println)
}