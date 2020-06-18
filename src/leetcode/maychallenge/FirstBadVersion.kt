package leetcode.maychallenge

/* The isBadVersion API is defined in the parent class VersionControl.
      def isBadVersion(version: Int): Boolean = {} */
private object FirstBadVersion {
    fun firstBadVersion(n: Int): Int {
        var left = 1
        var right = n
        while (left < right) {
            val mid = left + (right - left) / 2
//            if (isBadVersion(mid)) { // works in leetcode
//                right = mid
//            } else {
//                left = mid + 1
//            }
        }
        return left
    }
}

fun main() {

}