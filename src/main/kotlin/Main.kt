fun main() {
    val days = listOf(Day1(), Day2(), Day3(), Day4())
    days.forEach { day ->
        println("${day.javaClass.simpleName} - puzzle1: ${day.solve1()}")
        println("${day.javaClass.simpleName} - puzzle2: ${day.solve2()}")
    }
}
