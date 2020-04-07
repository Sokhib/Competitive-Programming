package dersler

class Photo {


    val photoUrl = "${BASE_URL}Doodle"
    val photoUrl2 = "${BASE_URL}Hello"

    // JvmStatic Java class'indan cagirdigimizda gerekiyor
    //
    companion object State {
        @JvmStatic
        var BASE_URL = "www.google.com/"
    }
}

fun main() {
    val photo = Photo()
    photo.photoUrl.log()
    photo.photoUrl2.log()
    Photo.BASE_URL = "www.yandex.com/"
    val photo2 = Photo()
    photo2.photoUrl.log()
    photo2.photoUrl2.log()


}