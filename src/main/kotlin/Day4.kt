import FileUtil.Companion.puzzleLines
import kotlin.math.pow

class Day4: DailyPuzzle() {
    override fun solve1(): String {
        val lines = puzzleLines(4)

        val sum = lines.map { Card.of(it) }
            .sumOf { (2.0).pow(it.matchingCards() - 1).toInt() }

        return sum.toString()
    }

    data class Card(val id: Int, val winningNumbers: Set<Int>, val myNumbers: Set<Int>) {

        companion object {
            fun of(line: String): Card {
                val parts = line.split(':', '|')

                return Card(
                    intFrom(parts.first()),
                    parts[1].trim().split(' ').filter { it.isNotEmpty() }.map { it.toInt() }.toSet(),
                    parts[2].trim().split(' ').filter { it.isNotEmpty() }.map { it.toInt() }.toSet(),
                )
            }

            private fun intFrom(text: String): Int {
                return text.filter { it.isDigit() }
                    .toInt()
            }
        }

        fun matchingCards(): Int {
            return winningNumbers.intersect(myNumbers).size
        }

    }

    override fun solve2(): String {
        return ""
    }

}