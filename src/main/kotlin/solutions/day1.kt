package solutions

import utils.AdventSolver

object Day1 {
    fun run() {
        solve(AdventSolver.getData(1))
    }

    private fun solve(data: MutableList<String>) {
        val groupByElfLists = mutableListOf<Int>()
        val nextElfList = mutableListOf<Int>()
        data.map {
            if (it.isEmpty()) {
                groupByElfLists.add(nextElfList.sum())
                nextElfList.clear()
            } else {
                nextElfList.add(it.toInt())
            }
        }
        groupByElfLists.sortDescending()
        val maxCaloriesByElf = groupByElfLists.first()
        AdventSolver.printAnswer(1, 1, maxCaloriesByElf)

        val topThreeCaloriesTotal = groupByElfLists.take(3).sum()
        AdventSolver.printAnswer(1, 2, topThreeCaloriesTotal)
        // correct answers: 70116, 206582
    }
}