package leetcode

class Solution1 {
    fun defangIPaddr(address: String): String {
        return address.replace(".", "[.]")
    }
}


fun main() {
    val sol1 = Solution1()
    println(sol1.defangIPaddr("255.100.50.0"))
}