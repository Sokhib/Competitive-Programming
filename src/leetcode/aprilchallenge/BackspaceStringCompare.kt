package leetcode.aprilchallenge

object BackspaceStringCompare {
    fun backspaceCompare(S: String, T: String): Boolean {
        val reg = Regex("[a-z]#")
        var sWithout = S
        var tWithout = T
        while (sWithout.contains("#")) {
            sWithout = reg.replace(sWithout, "")
            if (!sWithout.contains(reg)) sWithout = sWithout.replace("#", "")
        }
        while (tWithout.contains("#")) {
            tWithout = reg.replace(tWithout, "")
            if (!tWithout.contains(reg)) tWithout = tWithout.replace("#", "")
        }
        return sWithout == tWithout
    }
}

fun main() {
    BackspaceStringCompare.backspaceCompare("ab##", "c#d#").also(::println)
    BackspaceStringCompare.backspaceCompare("a##c", "#a#c").also(::println)
    BackspaceStringCompare.backspaceCompare("du###vu##v#fbtu", "du###vu##v##fbtu").also(::println)


}