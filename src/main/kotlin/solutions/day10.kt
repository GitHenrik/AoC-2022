package solutions

import utils.AdventSolver.getData
import utils.AdventSolver.printAnswer

object Day10 {
    fun run() {
        val data = getData(10)
        solve(data)
    }

    private fun solve(data: MutableList<String>) {
        var registerValue = 1
        val clock = Clock()
        printAnswer(10, 2, "")
        for (i in 0 until data.size) {
            if (data[i] == "noop") {
                clock.update(registerValue)
                continue
            }
            val (instruction, incrementValue) = data[i].split(' ')
            if (instruction == "addx") {
                clock.update(registerValue)
                clock.update(registerValue)
                registerValue += incrementValue.toInt()
            } else {
                println("puki")
            }
        }
        print("\n")
        printAnswer(10, 1, clock.signalStrength)
    }
}

class Clock {
    var cycle = 0
    var signalStrength = 0
    var signalPoints = listOf(20, 60, 100, 140, 180, 220)

    private fun increment(registerValue: Int) {
        cycle++
        if (signalPoints.any { it == cycle}) {
            signalStrength += cycle * registerValue
        }
    }
    private fun draw(registerValue: Int) {
        if (cycle % 40 == 0) {
            print("\n")
        }
        if (cycle % 40 == registerValue ||
            cycle % 40 == registerValue + 1 ||
            cycle % 40 == registerValue - 1) {
            print('#')
        } else {
            print('.')
        }
    }

    fun update(registerValue: Int) {
        draw(registerValue)
        increment(registerValue)
    }
}