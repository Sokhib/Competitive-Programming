package dersler

/*
* object acts as static cannot be erased by garbage collector
*
*   volatile ayni anda bir thread erisebilir.
*   synchronized methodu ayni anda bir thread erisebilir.
*   Classin constructor'unu private yap Default olan parametresiz cons private yap
*   nesnesini private static yaz
*   private static olan nesneyi get methoduyla null control'u yaparak geri dondur
*
*
*
*
*
*
*
* */
object SingletonObject {
    var color = "blue"
    fun printColor() {
        println(color)
    }
}

fun main() {
    SingletonObject.color = "cyan"
    SingletonObject.printColor()
}