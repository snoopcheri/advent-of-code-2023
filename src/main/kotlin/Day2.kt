import Day2.Game.Companion.toGame
import FileUtil.Companion.puzzleLines
import kotlin.math.max

class Day2: DailyPuzzle() {

    private val maxCubes: Cubes = Cubes(12, 13, 14)

    override fun solve1(): String {
        val lines = puzzleLines(2)

        val sum = lines.map { line -> toGame(line) }
            .filter { game ->game.isValidFor(maxCubes) }
            .sumOf { game -> game.id }

        return sum.toString()
    }

    override fun solve2(): String {
        val lines = puzzleLines(2)

        val sum = lines.map { line -> toGame(line) }
            .map { game -> game.minCubes() }
            .sumOf { cubes -> cubes.red * cubes.green * cubes.blue }

        return sum.toString()
    }

    private data class Cubes(val red: Int, val green: Int, val blue: Int)

    private data class Game(val id: Int, val rounds: List<Cubes>) {

        companion object {
            fun toGame(line: String): Game {
                // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                val parts = line.split(':', ';')
                val id = intFrom(parts.first())
                val rounds = parts.drop(1)
                    .map { text -> toCubes(text) }
                    .toList()

                return Game(id, rounds)
            }

            private fun intFrom(text: String): Int {
                return text.filter { it.isDigit() }
                    .toInt()
            }

            private fun toCubes(text: String): Cubes {
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

                return Cubes(red, green, blue)
            }
        }

        fun isValidFor(maxCubes: Cubes): Boolean {
            for (round in rounds) {
                if (round.red > maxCubes.red || round.green > maxCubes.green || round.blue > maxCubes.blue) {
                    return false
                }
            }

            return true
        }

        fun minCubes(): Cubes {
            var red: Int? = null
            var green: Int? = null
            var blue: Int? = null

            for (round in rounds) {
                red = maxOf(red, round.red)
                green = maxOf(green, round.green)
                blue = maxOf(blue, round.blue)
            }

            if (red == null || green == null || blue == null) {
                throw IllegalArgumentException("All red, green and blue must be set")
            }

            return Cubes(red, green, blue)
        }

        private fun maxOf(current: Int?, newValue: Int): Int {
            return if (current == null) {
                newValue
            } else {
                max(current, newValue)
            }
        }

    }

}
