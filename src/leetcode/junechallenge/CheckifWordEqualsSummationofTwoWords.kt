package leetcode.junechallenge

fun isSumEqual(firstWord: String, secondWord: String, targetWord: String): Boolean {
    val stringBuilder = StringBuilder()
    firstWord.forEach {
        stringBuilder.append(it - 'a')
    }
    var leftResult = stringBuilder.toString().toInt()
    stringBuilder.clear()
    secondWord.forEach {
        stringBuilder.append(it - 'a')
    }
    leftResult += stringBuilder.toString().toInt()
    stringBuilder.clear()
    targetWord.forEach {
        stringBuilder.append(it - 'a')
    }
    val rightResult = stringBuilder.toString().toInt()
    return leftResult == rightResult
}

fun main() {
    println(isSumEqual("j", "j", "bi"))
//    println(isSumEqual("acb", "cba", "cdb"))
//    assertEquals(
//        true, isSumEqual("acb", "cba", "cdb")
//    )
}