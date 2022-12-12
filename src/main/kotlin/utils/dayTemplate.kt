package utils

import utils.AdventSolver.getData
import utils.AdventSolver.printAnswer

object DayTemplate {
    fun run() {
        val data = getData(-1)
        solvePartOne(data)
        solvePartTwo(data)
    }

    private fun solvePartOne(data: MutableList<String>) {
        // TODO
        printAnswer(-1, 1, -1)
    }

    private fun solvePartTwo(data: MutableList<String>) {
        // TODO
        printAnswer(-1, 2, -1)
    }
}