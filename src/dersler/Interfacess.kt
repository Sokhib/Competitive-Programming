package dersler

/*

    interface bir class degil
    interface'te init{izin verilmez}
    interface constructor'u yoktur.
    interface icinde this::class implement edildigi class'i aliyor
    Eger bir degisken yaratilacaksa, eger bir isin yeteneklerini degil de isin tum detaylarini anlatmaya baslasak...


* Abstract class'lar interface'lerin yapabildigi herseyi yapabilirler...
* + Ustelik extra olarak degiskenlerin degerlerini saklayabilirler...
* Interfaceler degisken degerlerini saklayamazlar
*
    Interface'ler bir class'a kazandirmak istedigimiz yetenekleri ifade ediyor)

* Interfaces are like sozlesme, as every sozlesme butun maddelerine(methodlarina) uymak zorunda(implement etmek zorunda)
*
*Body verdigimiz interface methodunu override etmek zorunda degiliz...
*
*
* Bir class 1+ classtan EXTEND edemez, ama 1+ interface IMPLEMENT edebilir
*
*
* Eger interface'in method'unun body'si varsa, BU interface'i implement ettigimiz class'larda method'unu override etmek zorunda degiliz ve
*               implement ettigimiz classlarin objesinde ABOVE METHOD'u cagirabiliriz.
*
*
*
* */
interface AnimalEvent {
    fun doesMove(doesFly: Boolean, doesSwim: Boolean, doesWalk: Boolean) {
        when (this::class.simpleName.toString()) {
            "dersler.Fish" -> "dersler.Fish".log()
            "dersler.Bird" -> "dersler.Bird".log()
            "dersler.Cats" -> "dersler.Cats".log()

        }
        println("interface: doesFly: $doesFly, doesSwim: $doesSwim, doesWalk: $doesWalk ")
    }

    fun makeSound() {
        println("Make a sound")
    }

    fun eat(isCarnivorous: Boolean)
}

interface WalkEvent {
    fun fastSpeed()
    fun slowSpeed()
}


open class Animal {
    open fun doesMove(doesFly: Boolean, doesSwim: Boolean, doesWalk: Boolean) {
        println("class: doesFly: $doesFly, doesSwim: $doesSwim, doesWalk: $doesWalk ")
    }
}


class Fish : AnimalEvent {
    override fun doesMove(doesFly: Boolean, doesSwim: Boolean, doesWalk: Boolean) {

    }

    override fun makeSound() {

    }

    override fun eat(isCarnivorous: Boolean) {

    }

}

class Bird : AnimalEvent {

    override fun eat(isCarnivorous: Boolean) {
    }

}

class Cats : Animal(), WalkEvent,
    AnimalEvent {
    override fun doesMove(doesFly: Boolean, doesSwim: Boolean, doesWalk: Boolean) {
        super<AnimalEvent>.doesMove(doesFly, doesSwim, doesWalk)
    }

    override fun eat(isCarnivorous: Boolean) {
    }

    override fun fastSpeed() {
    }

    override fun slowSpeed() {
    }
}

fun main() {
    val cats = Cats()
    val fish = Fish()
    val bird = Bird()
    cats.doesMove(false, false, true)
    fish.doesMove(false, true, false)
    bird.doesMove(true, false, true)
    cats.makeSound()

}

// Abstract base yapilari(class)'lari yazarken genelde
// abstract keyword'u varsa zorunlu olarak override edilmeli
abstract class BaseAnimal {
    abstract val name: String
}

class SivasKangali(override val name: String) : BaseAnimal()


fun String.log() {
    println(this)
}