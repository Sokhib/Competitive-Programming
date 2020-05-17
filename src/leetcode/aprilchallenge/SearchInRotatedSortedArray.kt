package leetcode.aprilchallenge

private object SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        if (nums.contains(target)) return nums.indexOf(target)
        return -1
    }
}

fun main() {
    SearchInRotatedSortedArray.search(intArrayOf(4, 5, 6, 7, 0, 1, 2, 3), 0).also(::println)
}