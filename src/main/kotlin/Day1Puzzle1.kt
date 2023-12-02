import FileUtil.Companion.puzzleLines
import java.nio.file.Paths

class Day1Puzzle1 {
    companion object {
        fun solve() {
            println(Paths.get("").toAbsolutePath().toString())
            val lines = puzzleLines(1, 1)

            val sum = lines.sumOf { line ->
                val first = line.first { it.isDigit() }.digitToInt()
                val last = line.last { it.isDigit() }.digitToInt()
                first * 10 + last
            }

            println(sum)
        }
    }
}
