package solutions

import utils.AdventSolver
import utils.AdventSolver.getData

object Day8 {
    fun run() {
        val data = getData(8)
        solvePartOne(data)
        solvePartTwo(data)
    }

    private fun solvePartOne(data: MutableList<String>) {
        val rowIndexes = data.size - 1
        val columnIndexes = data.first().length - 1 // assume all rows are the same length
        var visibleTrees = 0
        for (column in 0..columnIndexes) {
            for (row in 0..rowIndexes) {
                if (column == 0 || column == columnIndexes || row == 0 || row == rowIndexes) {
                    visibleTrees++
                    continue
                }
                if (isTreeVisibleByRow(column, data[row])) {
                    visibleTrees++
                    continue
                }
                if (isTreeVisibleByColumn(data, row, column)) {
                    visibleTrees++
                    continue
                }

            }
        }
        AdventSolver.printAnswer(8, 1, visibleTrees)
    }

    private fun isTreeVisibleByColumn(data: MutableList<String>, treeRowIndex: Int, treeColumnIndex: Int): Boolean {
        val treeHeight = data[treeRowIndex][treeColumnIndex].digitToInt()
        // check above
        var visibleFromUp = true
        for (row in 0 until treeRowIndex) {
            if (data[row][treeColumnIndex].digitToInt() >= treeHeight) visibleFromUp = false
        }
        // check below, start from the row below the current tree
        var visibleFromDown = true
        for (row in treeRowIndex + 1 until data.size) {
            if (data[row][treeColumnIndex].digitToInt() >= treeHeight) visibleFromDown = false
        }
        return visibleFromUp || visibleFromDown
    }


    private fun isTreeVisibleByRow(treeColumnIndex: Int, row: String): Boolean {
        val treeHeight = row[treeColumnIndex].digitToInt()
        val left = row.substring(0, treeColumnIndex)
        val right = row.substring(treeColumnIndex + 1, row.length)
        val visibleFromLeft = left.all { it.digitToInt() < treeHeight }
        val visibleFromRight = right.all { it.digitToInt() < treeHeight }
        return visibleFromLeft || visibleFromRight
    }

    private fun solvePartTwo(data: MutableList<String>) {
        // TODO
        AdventSolver.printAnswer(8, 2, -1)
    }
}