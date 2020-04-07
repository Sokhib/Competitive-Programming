package dersler

// Eger TextView implements Interface yapmak istemiyorsak, ama yinede objesine implement yapmak istiyorsak
// let's consider TextView has 200 child classes,
// in this case we have to implement OnTextChanged on every child with this method we won't have to do this
// olusturdugumuz nesneye interface veriyoruz...


open class TextView(text: String) {
    open val textSize = text.length
    open var textMessage = ""
}

interface OnTextChanged {

    fun textChanged()
}

fun main() {
    val textView = object : TextView("hello"), OnTextChanged {
        override val textSize: Int
            get() = textMessage.length

        override fun textChanged() {
            textMessage = "Changed"
            println(this.textSize)
            println(this.textMessage)
        }
    }

    textView.textChanged()


    val student = object {
        val name = "So"
        val age = 22
    }
    println("${student.name}  ${student.age}")

    //lol
    var c = 4
    c -= -1
    println(c)
    val rowOne = "https:\t//google.com/dmsad"
    val raw = """https:\t//google.com"""
    println(raw)
    println(rowOne)

}