package dersler

object BedilsProblemOfConference {
    fun computeConferenceStrategy(
        data: Map<Pair<Int, Int>, Pair<Int, Double>>
    ) {
        val realData = data.toSortedMap(
            compareBy {
                it.first
            }
        )
        realData.forEach { (key, value) ->
            print("$key $value")
            println()
        }

    }
}

fun main() {
    BedilsProblemOfConference.computeConferenceStrategy(
        mapOf(
            Pair(0, 15) to Pair(100, 0.2),
            Pair(20, 35) to Pair(150, 0.15),
            Pair(10, 25) to Pair(180, 0.21)
        )
    )
}
