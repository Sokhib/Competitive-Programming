package dersler

/*
*   gruplayabilir ve sinirli data icin tercih edilir.
*   ENUM class CAN NOT EXTEND any or abstract class, BUT can IMPLEMENT interface
*  Eger enum'in constructoru varsa butun elementlerinin de constructor'u olmali
*  Directions(val code:Int)
*   EAST(code:Int) da olmali
* name ve ordinal degerleri final'dir, we can't OVERRIDE
*
*
* */
enum class Directions(val code: Int) {
    EAST(10) {
        override fun printValue() {
            println("${this.name} ${this.ordinal}")
        }
    },
    WEST(15) {
        override fun printValue() {
            println("${this.name} ${this.ordinal}")
        }
    },
    SOUTH(20) {
        override fun printValue() {
            println("${this.name} ${this.ordinal} ${this.code}")
        }
    },
    NORTH(25) {
        override fun printValue() {
            println("${this.name} ${this.ordinal}")
        }
    };

    abstract fun printValue()

}

fun main() {
    setDirection(Directions.EAST.name, Directions.EAST.ordinal)
    setDirection(Directions.WEST.name, Directions.WEST.ordinal)
    setDirection(Directions.SOUTH.name, Directions.SOUTH.ordinal)
    Directions.NORTH.printValue()

}

fun setDirection(direction: String, position: Int) {
    println("$direction : $position")
}
