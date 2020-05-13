package leetcode.aprilchallenge

object ValidParenthesisString {
    fun checkValidString(s: String): Boolean {
        var balance = 0
        for (i in s.indices) {
            if (s[i] == '(' || s[i] == '*') ++balance
            else --balance
            if (balance == -1) return false
        }
        balance = 0
        for (i in s.length - 1 downTo 0) {
            if (s[i] == ')' || s[i] == '*') ++balance
            else --balance
            if (balance == -1) return false
        }
        return true
    }
}

fun main() {
//    ValidParenthesisString.checkValidString("(*())").also(::println)
//
//    ValidParenthesisString.checkValidString("(**))()").also(::println)

//    ValidParenthesisString.checkValidString("(*))").also(::println)

    ValidParenthesisString.checkValidString("**(").also(::println)

    ValidParenthesisString.checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*").also(::println)

//    ValidParenthesisString.checkValidString("(**))())").also(::println)
//    ValidParenthesisString.checkValidString("(*()").also(::println)
//    ValidParenthesisString.checkValidString("()").also(::println)
//    ValidParenthesisString.checkValidString("(*)").also(::println)
}