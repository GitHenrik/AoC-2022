package solutions

import utils.AdventSolver
import utils.AdventSolver.getData

object Day5 {

    fun run() {
        val data = getData(5)

        // get initial stacks
        val initialStacks = data.slice(0..7).reversed()
        val trimmedInitialStacks = trimStacks(initialStacks)
        // Parser WIP
        // trimmedInitialStacks.forEach(::println)

        val sortedStacks = mutableListOf(
                "WRF",
                "THMCDVWP",
                "PMZNL",
                "JCHR",
                "CPGHQTB",
                "GCWLFZ",
                "WVLQZJGC",
                "PNRFWTVC",
                "JWHGRSV",
            )

        val otherStacks = mutableListOf(
            "WRF",
            "THMCDVWP",
            "PMZNL",
            "JCHR",
            "CPGHQTB",
            "GCWLFZ",
            "WVLQZJGC",
            "PNRFWTVC",
            "JWHGRSV",
        )

        // get instructions
        val instructions = data.subList(10, data.size)

        for (i in 0 until instructions.size) {
            modifyStacks(sortedStacks, instructions[i], true)
        }

        for (i in 0 until instructions.size) {
            modifyStacks(otherStacks, instructions[i], false)
        }

        var answerOne = ""
        var answerTwo = ""

        for (sortedStack in sortedStacks) {
            answerOne += sortedStack.last() // CVCWCRTVQ
        }

        for (sortedStack in otherStacks) {
            answerTwo += sortedStack.last()  // CNSCZWLVT
        }
        AdventSolver.printAnswer(5, 1, answerOne)
        AdventSolver.printAnswer(5, 2, answerTwo)

    }

    private fun modifyStacks(stacks: MutableList<String>, instruction: String, reversed: Boolean): MutableList<String> {
        val splitted = instruction.split(' ')
        val move = splitted[1].toInt()
        val from = splitted[3].toInt() - 1
        val to = splitted[5].toInt() - 1
        if (reversed) {
            val stringToMove = stacks[from].substring(stacks[from].length - move, stacks[from].length).reversed()
            stacks[from] = stacks[from].dropLast(move)
            stacks[to] += stringToMove
        } else {
            val stringToMove = stacks[from].substring(stacks[from].length - move, stacks[from].length)
            stacks[from] = stacks[from].dropLast(move)
            stacks[to] += stringToMove
        }
        return stacks
    }

    private fun trimStacks(initialStacks: List<String>): List<String> {
        var trimmed = mutableListOf<String>()
        for (line in initialStacks) {
            var nextLine = ""
            for (i in 1..line.length step 4) {
                nextLine += line[i]
            }
            trimmed.add(nextLine)
        }
        return trimmed
    }

}