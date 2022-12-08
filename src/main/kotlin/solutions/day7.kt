package solutions

import utils.AdventSolver
import utils.AdventSolver.getData
import kotlin.math.floor

object Day7 {
    fun run() {
        val data = getData(7)
        solvePartOne(data)
        solvePartTwo(data)
    }



    var directories = mutableListOf<Directory>(Directory("/", "none"))
    var cwd = directories.find { it.name == "/" }

    private fun solvePartOne(data: MutableList<String>) {
        executeCommands(data)
        val sizeLimit = 100000
        var totalSizeOfSmallDirectories = 0
        directories.forEach { if (it.totalSize <= sizeLimit) totalSizeOfSmallDirectories += it.totalSize }

        AdventSolver.printAnswer(7, 1, totalSizeOfSmallDirectories) // 1844187
    }

    private fun executeCommands(commands: MutableList<String>) {
        if (commands.isEmpty()) return
        // line split into parts e.g. $ cd /
        val nextCommand = commands.first().split(' ')
        executeCommand(nextCommand)
        commands.removeFirst()
        executeCommands(commands)
    }

    private fun executeCommand(nextCommand: List<String>) {
        // do nothing with ls, cwd is still the current cwd
        if (nextCommand[1] == "ls" || nextCommand[0] == "dir") {
            return
        }
        else if (nextCommand[1] == "cd") {
            // cwd -> parent, add subdirectory size to parent size
            if (nextCommand[2] == "..") {
                val parent = directories.find { it.name == cwd?.parentDirectory }
                parent!!.totalSize += cwd!!.totalSize
                cwd = parent
            // change cwd
            } else {
                var newDir = Directory("${nextCommand[2]}${floor(Math.random()*2000)}", cwd!!.name)
                cwd!!.subdirectories.add(newDir)
                directories.add(newDir)
                cwd = newDir
            }

        }
        // is file -> add size to cwd size
        else if (nextCommand[0].toIntOrNull() != null) {
            cwd!!.totalSize += nextCommand[0].toInt()
        } else { // sanity check
            println("unhandled command: $nextCommand")
        }
    }

    private fun solvePartTwo(data: MutableList<String>) {
        // TODO
        AdventSolver.printAnswer(7, 2, -1)
    }
}

data class Directory(
    val name: String = "",
    val parentDirectory: String,
    var subdirectories: MutableList<Directory> = mutableListOf(),
    var totalSize: Int = 0,
)

