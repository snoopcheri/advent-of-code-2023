package day1.puzzle1

import java.nio.file.Paths

class Day1Puzzle1 {
    companion object {
        fun solve(): Unit {
            println(Paths.get("").toAbsolutePath().toString())
            val lines = this.javaClass.getResourceAsStream("puzzle.txt").bufferedReader().readLines()

            val sum = lines.sumOf { line ->
                val first = line.first { it.isDigit() }.digitToInt()
                val last = line.last { it.isDigit() }.digitToInt()
                first * 10 + last
            }

            println(sum)
        }
    }
}
