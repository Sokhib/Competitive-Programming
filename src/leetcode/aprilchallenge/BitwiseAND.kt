package leetcode.aprilchallenge

private object BitwiseAND {
    fun msbCount(n: Int): Int {
        var nn = n
        var msbC = -1
        while (nn > 0) {
            nn = nn shr 1
            ++msbC
        }
        return msbC
    }

    fun rangeBitwiseAnd(m: Int, n: Int): Int {
        var solution = 0
        var mm = m
        var nn = n
        while (mm > 0 && nn > 0) {
            val msbPositionM = msbCount(mm)
            val msbPositionN = msbCount(nn)
            if (msbPositionM != msbPositionN) return solution
            val msbVal = (1 shl msbPositionM)
            solution += msbVal
            mm -= msbVal
            nn -= msbVal
        }
        return solution
    }
}

fun main() {
    BitwiseAND.rangeBitwiseAnd(2, 16).also(::println)
    BitwiseAND.rangeBitwiseAnd(1, 7).also(::println)
    BitwiseAND.rangeBitwiseAnd(0, 1).also(::println)

}