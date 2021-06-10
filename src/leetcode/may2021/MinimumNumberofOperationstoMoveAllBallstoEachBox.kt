package leetcode.may2021

fun minOperations(boxes: String): IntArray {
    val result = IntArray(boxes.length) { 0 }
    boxes.forEachIndexed { indexI, _ ->
        boxes.forEachIndexed { indexJ, valueJ ->
            if (indexI != indexJ) {
                if (valueJ != '0') {
                }
                result[indexI] += Math.abs((indexJ - indexI))
            }
        }
    }
    return result
}

fun main() {
    minOperations("110").forEach { print("$it ") }
    minOperations("001011").forEach { print("$it ") }


}