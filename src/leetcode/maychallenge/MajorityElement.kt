package leetcode.maychallenge

private object MajorityElement {
    fun majorityElement(nums: IntArray): Int {
        val map = HashMap<Int, Int>(nums.size)
        nums.forEach {
            if (map[it] == null) {
                map[it] = 1
            } else {
                map[it] = map[it]!! + 1
            }
        }
        map.forEach { (key, values) ->
            if (values > nums.size / 2) return key
        }
        return 0
    }

    /*
    * fun majorityElement(nums: IntArray): Int {
        nums.forEach { element ->
            if (nums.count {
                    it == element
                } > nums.size / 2)
                return element
        }
        return 0
    }*/
}

fun main() {
    MajorityElement.majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2)).also(::println)
    MajorityElement.majorityElement(intArrayOf(3, 2, 3)).also(::println)
}