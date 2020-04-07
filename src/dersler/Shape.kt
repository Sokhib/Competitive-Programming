package dersler

/**
 *
 * internal makes Class public in module, and makes private outside...

class'in tanimini yapildigi yerde yada ustunde, top-level tanimlama








protected can't be written in top-level(next to class)
extend ettigimiz(from) class'larin icindeki degiskenlerde kullanacagiz...
protected can be val, fun and constructor...


protected yaparsak, torunlarinda kullanmak istedigimizde extend etmemiz gerekiyor...


 */
// class private olsa bile kendi file'i icinde extend ediliyorMUS
private open class Shape {

    open val shape: String = "shape"

    // protected yaparsak direk child classlara PUBLIC, onun disinda private... tekrar kullanma konusunda
    protected val number: Int = 3

    open fun draw() {
        println(shape)
    }

    // Ayni function yada ayni constructor'u farkli sayida parameterlerle degistirmek overload
    // Anlam degistirmeye ise override
    open fun fill() {

        println("fill")
    }

    open fun fill(name: String) {
        println(name)
    }

    open fun fill(count: Int): Int {
        println(count)
        return count
    }
}

// in order to extend from dersler.Shape class must be open
//Polymorphism olmasi icin baba class'a bir deger, anlam katmak gerekir.
private class Rectangle : Shape() {
//    init {
//        draw()
//    }

    //override val number = 1231

    override val shape: String
        get() = "dersler.Rectangle"

    override fun draw() {
        println("Drawing...")
        super.draw()
    }

}

//abstract class Circle() : dersler.Shape() {
//
//}

fun main() {
    val rec = Rectangle()
    rec.draw()
    //val circle = Circle() object of abstract class can not be created...


}