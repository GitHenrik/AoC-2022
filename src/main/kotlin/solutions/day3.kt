package day3

import utils.AdventSolver

object Day03 {
    fun run() {
        solvePartOne(AdventSolver.getData(3))
        solvePartTwo(AdventSolver.getData(3))
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
        println(totalPriority) // 2522
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
        println(totalPriority) // 8088
    }

    /*private fun findDuplicates(line: String): String {
        val first = line.slice(0 until line.length / 2)
        val second = line.slice(line.length / 2 until line.length)
        var duplicates = ""
        first.forEach {
            if (second.contains(it)) {
                duplicates += it
            }
        }
        return duplicates
    }*/

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

    /*private fun calculatePriorities(duplicates: String): Int {
        var priorityTotal = 0
        duplicates.forEach { priorityTotal += getPriority(it) }
        return priorityTotal
    }*/

    private fun getPriority(item: Char): Int {
        return priorityMap.get(item)!!
    }
}