package leetcode.maychallenge

private object RansomNote {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        var mMagazine = magazine
        ransomNote.forEach {
            if (mMagazine.contains(it)) {
                val index = mMagazine.indexOf(it)
                mMagazine = mMagazine.removeRange(index, index + 1)
            } else {
                return false
            }
        }
        return true
    }
}

fun main() {
    RansomNote.canConstruct("aab", "baa").also(::println)
    RansomNote.canConstruct("aab", "ba").also(::println)
    RansomNote.canConstruct(
        "fihjjjjei",
        "hjibagacbhadfaefdjaeaebgi"
    ).also(::println)
}