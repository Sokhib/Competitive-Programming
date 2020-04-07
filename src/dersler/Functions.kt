package dersler

import java.util.*
import kotlin.concurrent.schedule

fun main() {

    //Higher order function...
    printUserInfo(getName(), { surname ->
        "surname is: $surname"
    }, getAge())
    onClickListener { println("$it loaded!") }


}

fun printUserInfo(name: String, getSurName: (surname: String) -> String, age: Int) {
    println("$name $age")
    println(getSurName("Said"))
}


fun onClickListener(onClick: (String) -> Unit) {
    println("Loading Image...")
    Timer().schedule(3000) {
        onClick("wolf image")
    }

}


fun getName(): String {
    return "So"
}

fun getAge() = 25
