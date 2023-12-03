class FileUtil {

    companion object {

        fun exampleLines(day: Int): List<String> {
            return lines("example", day, null)
        }
        fun exampleLines(day: Int, puzzle: Int): List<String> {
            return lines("example", day, puzzle)
        }

        fun puzzleLines(day: Int): List<String> {
            return lines("puzzle", day, null)
        }

        fun puzzleLines(day: Int, puzzle: Int): List<String> {
            return lines("puzzle", day, puzzle)
        }

        private fun lines(type: String, day: Int, puzzle: Int?): List<String> {
            val fileName = if (puzzle == null) {
                "${type}/day${day}.txt"
            } else {
                "${type}/day${day}-puzzle${puzzle}.txt"
            }

            return javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
                ?: throw Exception("Could not find file $fileName")
        }
    }

}