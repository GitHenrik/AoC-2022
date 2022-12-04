package utils

import java.io.File

object AdventSolver {
    fun getData(day: Int): MutableList<String> {
        val data = mutableListOf<String>()
        File("src/main/kotlin/data/day${day}-data.txt").forEachLine { data.add(it) }
        return data
    }
}