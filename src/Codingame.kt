import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val Q = input.nextInt()
    val N = input.nextInt()
    var QQ = Q
    for (i in 0 until N) {
        val X = input.nextInt()
        val Y = input.nextInt()
        val Z = input.nextInt()

        QQ -= (X + Y + Z)

    }
    if (QQ >= 0) println(true)
    else println(false)
}