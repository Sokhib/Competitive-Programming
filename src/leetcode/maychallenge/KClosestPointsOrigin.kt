package leetcode.maychallenge

private fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
    val kClosest = HashMap<Double, ArrayList<IntArray>>()
    var distance: Double
    for (point in points) {
        val (x, y) = point
        distance = Math.sqrt((x * x + y * y).toDouble())
        if (kClosest.containsKey(distance)) {
            kClosest[distance]!!.add(point)
        } else {
            kClosest[distance] = arrayListOf(point)
        }
    }
    val sorted = kClosest.toSortedMap()
    val answer = Array(points.size) { intArrayOf() }
    var i = -1
    sorted.forEach { (_, value) ->
        value.forEach { p ->
            answer[++i] = p
        }
    }
    return answer.sliceArray(0 until K)
}

fun main() {
    kClosest(arrayOf(intArrayOf(0, 1), intArrayOf(1, 0)), 2).forEach {
        println("${it[0]} ${it[1]}")
    }

    kClosest(arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2)), 1).forEach {
        println("${it[0]} ${it[1]}")
    }
    //assertEquals(arrayOf(intArrayOf(-2, 2)), kClosest(arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2)), 1))

    kClosest(arrayOf(intArrayOf(-2, 4), intArrayOf(5, -1), intArrayOf(3, 3)), 2).forEach {
        println("${it[0]} ${it[1]}")
    }
}