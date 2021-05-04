package dersler

class OutClass<out T>(val value: T) {
    fun get(): T {
        return value
    }
}

class InClass<in T> {
    fun toString(value: T): String {
        return value.toString()
    }
}


fun main() {
    val out = OutClass("Hello")
    val ref: OutClass<Any> = out

    val innClass: InClass<Number> = InClass()
    println(innClass.toString(12))
}