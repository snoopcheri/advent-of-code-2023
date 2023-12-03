import FileUtil.Companion.puzzleLines

class Day1: DailyPuzzle() {

    override fun solve1(): String {
        val lines = puzzleLines(1, 1)

        val sum = lines.sumOf { line ->
            val first = line.first { it.isDigit() }.digitToInt()
            val last = line.last { it.isDigit() }.digitToInt()
            first * 10 + last
        }

        return sum.toString()
    }

    override fun solve2(): String {
        val lines = puzzleLines(1, 2)

        val sum = lines
            .map { replacedSpelledOutDigits(it) }
            .sumOf { line ->
                val first = line.first { it.isDigit() }.digitToInt()
                val last = line.last { it.isDigit() }.digitToInt()
                first * 10 + last
            }

        return sum.toString()
    }

    private val replacements = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )

    private fun replacedSpelledOutDigits(line: String): String {
        val result = StringBuilder()

        var idx = 0
        while (idx < line.length) {
            var replaced = false

            for (replacement in replacements) {
                if (line.substring(idx).startsWith(replacement.key)) {
                    result.append(replacement.value)
                    replaced = true
                    break
                }
            }

            if (!replaced) {
                result.append(line[idx])
            }

            idx++
        }

        return result.toString()
    }

}
