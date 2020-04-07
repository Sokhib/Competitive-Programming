/*
functions->Basics->vararg
getUserInfo() -> greater than number of Strings

* vararg'in basinda olan spread:)

/ *-+ infix || &&

*/


fun main() {

    val awesomeInstance = MyAwesomeClass()
    println("Power of: ${awesomeInstance.powerYap(2)}")

    val num2: Short = 255 // if out of range starts from 0
    val num: UByte = num2.toUByte()


    (2 derece 3).prnt()

    num.prnt()

    println(num)


}

//infix must be function of class or extension function
//infix methods MUST have SINGLE parameter.
class MyAwesomeClass {
    infix fun powerYap(powerOf: Int): Int {

        return powerOf
    }

}

fun Any.prnt() {
    println("$this")

}

infix fun Int.derece(number: Int) = Math.pow(this.toDouble(), number.toDouble())