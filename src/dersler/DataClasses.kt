package dersler

/**
 *
 *
 *
 * Ozunde data saklamak icin kullandigimiz classlar,
 * diger dillerde Transfer obj, Entity, POJO gibi gecen sadece degiskenlerin oldugu ve bunlarin getter/setter leri yazildigi classlardir...
 * MUST HAVE at least one primary constructor with val/var.
 *              4ozelligi
 * toString, equals(), hashCode(), copy()
 *Destructuring declaration bir funct degerinin iki degiskene atanabilir olmasini data classlar saglar (1 methodun sonucu olarak iki degeri alabiliyoruz)
 * withIndex returns data class
 *
 * In data classes if variable is in primary CONSTRUCTOR
 * when we copy it all the values will be copied,
 *  BUT
 *  if it's inside class default values will be provided
 * Data class'i print ettigimizde sadece PRIMARY CONSTRUCTOR degerlerini veriyor...
 *
 *
 * data classlar-> abstract, inner, sealed, open OLAMAZLAR.
 * data class can EXTEND or IMPLEMENT other classes.
 *
 *
 *
 * **/


data class Account(
    val accountName: String,
    val accountSurname: String,
    val branchName: String
) {
    val accountType = "BankAccount"
    val branchCode = "0223"
}

fun main() {
    val account = Account("So", "Said", "Mecidiye")
    val copyAccount = account.copy(accountName = "Soh1b")

    println(account.hashCode())
    println(copyAccount.hashCode())
    println(account)

    val pair = Pair<String, Int>("So", 1995)
    println("Name: ${pair.first} b.year: ${pair.second}")

}



