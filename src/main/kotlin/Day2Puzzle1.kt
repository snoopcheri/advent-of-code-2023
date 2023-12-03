import Day2Puzzle1.Game.Companion.toGame
import FileUtil.Companion.puzzleLines

class Day2Puzzle1 {

    companion object {
        fun solve() {
            val maxRed = 12
            val maxGreen = 13
            val maxBlue = 14
            val lines = puzzleLines(2, 1)

            val sum = lines.map { line -> toGame(line) }
                .filter { game ->game.isValidFor(maxRed, maxGreen, maxBlue) }
                .sumOf { game -> game.id }

            println(sum)
        }
    }

    private data class Game(val id: Int, val rounds: List<Triple<Int, Int, Int>>) {

        companion object {
            fun toGame(line: String): Game {
                // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                val parts = line.split(':', ';')
                val id = intFrom(parts.first())
                val rounds = parts.drop(1)
                    .map { text -> toRound(text) }
                    .toList()

                return Game(id, rounds)
            }

            private fun intFrom(text: String): Int {
                return text.filter { it.isDigit() }
                    .toInt()
            }

            private fun toRound(text: String): Triple<Int, Int, Int> {
                var red = 0
                var green = 0
                var blue = 0
                text.split(',')
                    .forEach {
                        if (it.contains("red")) {
                            red = intFrom(it)
                        } else if (it.contains("green")) {
                            green = intFrom(it)
                        } else if (it.contains("blue")) {
                            blue = intFrom(it)
                        } else {
                            throw IllegalArgumentException("Cannot parse $it")
                        }
                    }

                return Triple(red, green, blue)
            }
        }

        fun isValidFor(red: Int, green: Int, blue: Int): Boolean {
            for (round in rounds) {
                if (round.first > red || round.second > green || round.third > blue) {
                    return false
                }
            }

            return true
        }
    }
}
