package day1

import utils.AdventSolver

object Day01 {
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
        println("The most that an elf is carrying is $maxCaloriesByElf calories worth of food")

        val topThreeCaloriesTotal = groupByElfLists.take(3).sum()
        println("The top three Elves carrying the most Calories have a total of $topThreeCaloriesTotal calories")

        // correct answers: 70116, 206582
    }
}