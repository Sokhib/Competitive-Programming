package dersler

/*
    class'larin icerisinde enum kullanma yapabilmemizi saglayacak
    sinirladirilmis class hierarchy'si verecek.

     sealed class'tan object yaratilmasina izin verilmez.
*
*
* */
sealed class Fruit {
    class Apple : Fruit()
    class Banana : Fruit()
}

fun main() {
    val apple = Fruit.Apple()
    val banana = Fruit.Banana()

    printFruit(apple)
    printFruit(banana)

}

fun printFruit(fruit: Fruit) {
    when (fruit) {
        is Fruit.Apple -> {
            println("Apple")
        }
        is Fruit.Banana -> {
            println("Banana")
        }
    }
}