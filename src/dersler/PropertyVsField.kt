package dersler

// Javada degiskenler field, methodlar property
/*
*
* Class A{

val name:String = "Codemy"
var surname:String = "Sa"
}

val a  = A()
a.name cagirildiginda name'in get methodu cagirilir. name kendisi PRIVATE'tir.
a.surname = "Said" cagirildiginda surname'in setSurname methodu cagirilir.
Kotlin variable'lar direk property(get/set'i olan), NOT field
*
*
*
abstract -> interface(can't contain STATE) (state tutabilmeli, bir degiskenin degerini saklayabilmek)
*
*
*
Encapsulation ->
	Variables(Degisken) are private bu degiskeni erisen get/set methodlarin public olmasi durumuna encapsulation denir.

in Kotlin fun INSIDE fun bile encapsulation deniliyor.

Kotlin'de butun degiskenler PROPERTY...
*
* */

class AwesomeCodemy {
    val name = "Codemy"
    var link = "Twitch.Codemy"
    var website = "" // The same as making website VAL
        private set

    fun getMeWebsite() {
        website = "codemy"
        website = "codemy.Live"
        println(website)
    }

}

fun main() {
    val aweCodemy = AwesomeCodemy()
    val universe = 42
    println(universe)
    aweCodemy.getMeWebsite()

}
