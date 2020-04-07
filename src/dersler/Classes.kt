package dersler

//constructor bize bir nesneyi yaratabilmek icin neye ihtiyac duydugumuzu soyleyen yapi...

// Primary constructor -> className'in yanina yazilan
//
// Secondary constructorda val yada var tanimi yapilamaz
// Kotlinde class default olarak final, open yaparak extend yapabiliriz, variable, functionlari da ayni sekilde
//									Notlar
//**Java'da +1 constructor yazilirsa, default valid olmuyor.

//
// class dersler.Car /*private, @inject, @JvmOverloads*/ constructor(carType:String)
class Car(type: String = "without param, def value") {
    // Properties executed first, before constructor...
    val carType = "Metalic".also {
        println(it)
    }

    init {

        println("primary constructor type initin ici: $type")
        //Primary constructorlarin {} kullanimini yapmak icin
        // Secondary constructorlardan once calisacak
    }


    constructor(type: String, color: String) : this(type) { // If we have primary + secondary constructor,
        // we have to define with (this) primary constructor + give value if primary doesn't have
        println("secondary constructor type 2linin ici: $type, color: $color")
    }

    constructor(
        type: String,
        color: String,
        count: Int
    ) : this() { // this(type) yapilmadigi zaman primary constructorda olan type bu(2nd cons) type degeri almiyor.
        println("secondary constructor type uclunun ici: $type, color: $color, count: $count")
    }

}


class Car2(type: String, color: String) {
    var type = type
    val color = color

    init {
        this.type = type
    }

    fun printTypeAndColor() {
        println("$type car and $color color.")
    }
}


class Car3(val type: String, val color: String) {

    fun printTypeAndColor() {
        println("$type car and $color color.")
    }
}


fun main() {
    // val carIns1 = dersler.Car() //if we have default value it'll work
    // val carIns2 = dersler.Car("Mini")
    //    val carIns3 = dersler.Car("Mini", "black")
    //    val carIns4 = dersler.Car("Mini", "black", 3)

    val car = Car2("AudiQ3", "white")
    car.printTypeAndColor()


}

// instance can't be created, but both constructors should be private OR make class abstract...
class DontCreateInsFromMe private constructor() {
    private constructor(num: Int) : this()
}

// Difference is in abstract instance can't be created INSIDE class as well, BUT in private constructors instance can be created INSIDE class itself ...
abstract class DontCreateInsFromMeAbs


