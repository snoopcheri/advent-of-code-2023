import FileUtil.Companion.puzzleLines

class Day3 : DailyPuzzle() {
    override fun solve1(): String {
        val lines = puzzleLines(3)
        val matrix = Matrix(lines)
        val candidates = matrix.findCandidates()
        val sumOfPartNumbers = candidates
            .filter { it.isSurroundedBySymbol(matrix) }
            .sumOf { it.toPartNumber(matrix) }

        return sumOfPartNumbers.toString()
    }

    /**
     * We surround the parsed lines with a ring of dot.
     * This will make it easier to check for symbols since we can skip boundaries.
     */
    class Matrix(lines: List<String>) {
        private val width: Int
        private val height: Int
        val elems: Array<CharArray>

        init {
            width = lines.maxOf { it.length } + 2
            height = lines.size + 2
            elems = Array(height) { h ->
                if (h == 0 || h == height - 1) {
                    CharArray(width) { '.' }
                } else {
                    CharArray(width) { w ->
                        if (w == 0 || w == width - 1) {
                            '.'
                        } else {
                            lines[h - 1][w - 1]
                        }
                    }
                }
            }
        }

        private enum class Mode { SEARCHING, MATCHING }

        fun findCandidates(): List<Candidate> {
            return elems.flatMapIndexed { idx, row -> findCandidatesInRow(idx, row) }
        }

        private fun findCandidatesInRow(rowNr: Int, row: CharArray): List<Candidate> {
            val candidates = mutableListOf<Candidate>()
            var mode = Mode.SEARCHING
            var start = 0

            row.forEachIndexed { idx, c ->
                when (mode) {
                    Mode.SEARCHING -> {
                        if (c.isDigit()) {
                            mode = Mode.MATCHING
                            start = idx
                        }
                    }

                    Mode.MATCHING -> {
                        if (!c.isDigit()) {
                            mode = Mode.SEARCHING
                            candidates.add(Candidate(start, rowNr, idx - start))
                        }
                    }
                }
            }


            return candidates
        }

        override fun toString(): String {
            return elems.joinToString("\n") { String(it) }
        }
    }

    /**
     * A candidate is a range with digits in a row.
     * But we don't know yet whether it is surrounded by a symbol.
     */
    data class Candidate(val startX: Int, val rowNr: Int, val length: Int) {

        fun isSurroundedBySymbol(matrix: Matrix): Boolean {
            val currentRow = matrix.elems[rowNr]
            if (currentRow[startX - 1].isSymbol() || currentRow[startX + length].isSymbol()) {
                return true
            }

            val rowAbove = matrix.elems[rowNr - 1]
            val rowBelow = matrix.elems[rowNr + 1]
            for (idx in (startX - 1)..(startX + length)) {
                if (rowAbove[idx].isSymbol() || rowBelow[idx].isSymbol()) {
                    return true
                }
            }

            return false
        }

        fun toPartNumber(matrix: Matrix): Int {
            return String(matrix.elems[rowNr])
                .substring(startX, startX + length)
                .toInt()
        }

        private fun Char.isSymbol(): Boolean {
            return this != '.' && !this.isDigit()
        }
    }

    override fun solve2(): String {
        return "TODO"
    }

}
