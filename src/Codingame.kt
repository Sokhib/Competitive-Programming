import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    if (input.hasNextLine()) {
        input.nextLine()
    }
    val c = input.nextLine()

    for (i in 0 until n) {
        val p = input.nextInt()
        print("${c[p - 1]}")
    }
}