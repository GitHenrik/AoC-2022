package solutions

import utils.AdventSolver

object Day3 {
    fun run() {
        solvePartOne(AdventSolver.getData(3))
        solvePartTwo(AdventSolver.getData(3))
    }

    private fun getSameItemFromElfGroup(first: String, second: String, third: String): Char {
        return first.toCharArray().intersect(second.asIterable().toSet()).intersect(third.asIterable().toSet()).first()
    }

    private fun getPriorityMap(): Map<Char, Int> {
        val charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var currentPriority = 1
        val valueMap = mutableMapOf<Char, Int>()
        charSet.forEach {
            valueMap.put(it, currentPriority)
            currentPriority++
        }
        valueMap['.'] = 0
        return valueMap
    }

    private val priorityMap = getPriorityMap()

    private fun solvePartOne(data: MutableList<String>) {
        var totalPriority = 0
        data.forEach { totalPriority += getPriority(findDuplicate(it)) }
        AdventSolver.printAnswer(3, 1, totalPriority) // 8088
    }

    private fun solvePartTwo(data: MutableList<String>) {
        var totalPriority = 0
        val elfGroupList = data.chunked(3)
        elfGroupList.forEach {
            totalPriority += getPriority(
                getSameItemFromElfGroup(
                    it[0], it[1], it[2]
                )
            )
        }
        AdventSolver.printAnswer(3, 2, totalPriority) // 2522
    }

    private fun findDuplicate(line: String): Char {
        val first = line.slice(0 until line.length / 2)
        val second = line.slice(line.length / 2 until line.length)
        first.forEach {
            if (second.contains(it)) {
                return it
            }
        }
        return '.'
    }

    private fun getPriority(item: Char): Int {
        return priorityMap.get(item)!!
    }
}