package solutions

import utils.AdventSolver
import utils.AdventSolver.getData

object Day6 {
    fun run() {
        val data = getData(6).first()
        solvePartOne(data)
        solvePartTwo(data)
    }

    private fun solvePartOne(data: String) {
        val answer = findUniqueWindowEndPosition(data, 4)
        AdventSolver.printAnswer(6, 1, answer)

    }

    private fun solvePartTwo(data: String) {
        val answer = findUniqueWindowEndPosition(data, 14)
        AdventSolver.printAnswer(6, 2, answer)
    }

    private fun findUniqueWindowEndPosition(data: String, windowSize: Int): Int {
        val windows = data.windowed(windowSize, 1)
        windows.forEachIndexed { index, value ->
            if (value.toSet().size == windowSize) {
                return index + windowSize
            }
        }
        return -1
    }
}