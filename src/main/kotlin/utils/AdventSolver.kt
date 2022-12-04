package utils

import java.io.File

object AdventSolver {
    fun getData(day: Int): MutableList<String> {
        val data = mutableListOf<String>()
        File("src/main/kotlin/data/day${day}-data.txt").forEachLine { data.add(it) }
        return data
    }

    /**
     * It is assumed that the answer is always a number
     */
    fun printAnswer(day: Int, part: Int, answer: Int) {
        println("Day $day, part $part: $answer")
    }
}