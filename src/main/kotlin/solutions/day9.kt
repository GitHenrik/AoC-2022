package solutions

import utils.AdventSolver.printAnswer
import utils.AdventSolver.getData

object Day9 {
    fun run() {
        val data = getData(9)
        solvePartOne(data)
        solvePartTwo(data)
    }

    private fun solvePartOne(data: MutableList<String>) {
        val head = SpaceTraverser(0, 0)
        val tail = SpaceTraverser(0, 0)
        val headPositions = mutableListOf(Pair(0, 0))
        val tailPositions = mutableListOf(Pair(0, 0))

        performMovements(head, tail, data, headPositions, tailPositions)
        val uniqueTailPositions = tailPositions.toSet()
        printAnswer(9, 1, uniqueTailPositions.size) // 6090
    }

    private fun performMovements(
        head: SpaceTraverser,
        tail: SpaceTraverser,
        movements: MutableList<String>,
        headPositions: MutableList<Pair<Int, Int>>,
        tailPositions: MutableList<Pair<Int, Int>>
    ) {
        movements.forEach {
            with(it.first()) {
                if (this == 'U') for (i in 1..it.split(' ').last().toInt()) {
                    head.moveUp()
                    headPositions.add(Pair(head.x, head.y))
                    updateTailPosition(head, tail, tailPositions)
                }
                if (this == 'D') for (i in 1..it.split(' ').last().toInt()) {
                    head.moveDown()
                    headPositions.add(Pair(head.x, head.y))
                    updateTailPosition(head, tail, tailPositions)
                }
                if (this == 'L') for (i in 1..it.split(' ').last().toInt()) {
                    head.moveLeft()
                    headPositions.add(Pair(head.x, head.y))
                    updateTailPosition(head, tail, tailPositions)
                }
                if (this == 'R') for (i in 1..it.split(' ').last().toInt()) {
                    head.moveRight()
                    headPositions.add(Pair(head.x, head.y))
                    updateTailPosition(head, tail, tailPositions)
                }
            }
            headPositions.add(Pair(head.x, head.y))
        }

    }

    private fun updateTailPosition(head: SpaceTraverser, tail: SpaceTraverser, tailPositions: MutableList<Pair<Int, Int>>) {
        // check all directions towards the right
        if (head.x == tail.x + 2 && head.y == tail.y) {
            tail.moveRight()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x + 2 && head.y == tail.y + 1) {
            tail.moveDownRight()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x + 2 && head.y == tail.y - 1) {
            tail.moveUpRight()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        // check all directions towards the left
        else if (head.x == tail.x - 2 && head.y == tail.y) {
            tail.moveLeft()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x - 2 && head.y == tail.y + 1) {
            tail.moveDownLeft()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x - 2 && head.y == tail.y - 1) {
            tail.moveUpLeft()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        // check all directions upwards
        else if (head.x == tail.x && head.y == tail.y - 2) {
            tail.moveUp()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x + 1 && head.y == tail.y - 2) {
            tail.moveUpRight()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x - 1 && head.y == tail.y - 2) {
            tail.moveUpLeft()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        // check all directions downwards
        else if (head.x == tail.x && head.y == tail.y + 2) {
            tail.moveDown()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x + 1 && head.y == tail.y + 2) {
            tail.moveDownRight()
            tailPositions.add(Pair(tail.x, tail.y))
        }
        else if (head.x == tail.x - 1 && head.y == tail.y + 2) {
            tail.moveDownLeft()
            tailPositions.add(Pair(tail.x, tail.y))
        }
    }

    private fun solvePartTwo(data: MutableList<String>) {
        // TODO
        printAnswer(9, 2, -1)
    }
}

class SpaceTraverser(var x: Int, var y: Int) {
    fun moveRight() = x++
    fun moveLeft() = x--
    fun moveUp() = y--
    fun moveDown() = y++
    fun moveUpRight() {
        x++
        y--
    }
    fun moveUpLeft() {
        x--
        y--
    }
    fun moveDownRight() {
        x++
        y++
    }
    fun moveDownLeft() {
        x--
        y++
    }

    override fun toString(): String {
        return "$x, $y"
    }
}