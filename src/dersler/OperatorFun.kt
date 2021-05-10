package dersler

class Greeting(private val greeting: String) {
    operator fun invoke(target: String) = println("$greeting $target")
}

fun main() {
    val a = Greeting("Hey")
    a("Zibab")
}