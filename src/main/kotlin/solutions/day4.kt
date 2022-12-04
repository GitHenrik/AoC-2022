package solutions

import utils.AdventSolver
import utils.AdventSolver.getData

object Day4 {
    fun run() {
        solve(getData(4))
    }

    private fun solve(data: MutableList<String>) {
        var subsetCount = 0
        var intersectionCount = 0
        data.forEach {
            val (firstElf, secondElf) = getPairBoundaries(it)
            val firstElfBoudaries = getSectionBoundaries(firstElf)
            val secondElfBoudaries = getSectionBoundaries(secondElf)
            if (isSubset(firstElfBoudaries, secondElfBoudaries)) subsetCount++
            if (hasIntersection(firstElfBoudaries, secondElfBoudaries)) intersectionCount++
        }

        AdventSolver.printAnswer(4, 1, subsetCount) // 562
        AdventSolver.printAnswer(4, 2, intersectionCount) // 924
    }

    private fun getSectionBoundaries(range: String): Pair<Int, Int> {
        val boundaries = range.split('-')
        return Pair(boundaries.first().toInt(), boundaries.last().toInt())
    }

    private fun getPairBoundaries(pairData: String): Pair<String, String> {
        val boundaries = pairData.split(',')
        return Pair(boundaries[0], boundaries[1])
    }

    private fun isSubset(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return if (a.first >= b.first && a.second <= b.second) {
            true
        } else b.first >= a.first && b.second <= a.second
    }

    private fun hasIntersection(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return !(a.second < b.first || b.second < a.first)
    }
}