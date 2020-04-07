package dersler

class School {
    val schoolName = "Codemy"
    private val schoolType = "Online"

    inner class TechSchool {
        val techStudentCount = 5000

        fun printOuterName() {
            println("Outer School Name: $schoolName")
            println("Outer School Type: $schoolType")

        }

        inner class ProgrammingSchool
    }

    //Nested class
    class MedSchool {
        val medStudentCount = 2300

        fun printOuterName() {
            //cannot access obj of outer class
            // println("Outer: $schoolName")


        }
    }
}


fun main() {
    val medSchool = School.MedSchool()
    val techSchool = School().TechSchool()

    medSchool.printOuterName()
    techSchool.printOuterName()
    //techSchool.schoolName // yok
    //val programmingSchool = School().TechSchool().ProgrammingSchool()


}