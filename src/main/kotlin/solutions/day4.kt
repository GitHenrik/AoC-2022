package solutions

import utils.AdventSolver
import utils.AdventSolver.getData

class Day4 {
    companion object {
        fun run() {
            solvePartOne(getData(4))
            solvePartTwo(getData(4))
        }

        private fun solvePartOne(data: MutableList<String>) {
            val first = data.first()

            AdventSolver.printAnswer(4, 1, -1)
        }

        private fun solvePartTwo(data: MutableList<String>) {
            // TODO
            AdventSolver.printAnswer(4, 2, -1)
        }
    }
}