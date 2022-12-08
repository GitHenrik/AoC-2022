package solutions

import utils.AdventSolver
import utils.AdventSolver.getData
import java.io.File
import kotlin.math.floor

object Day7 {
    fun run() {
        val data = getData(7)
        solvePartOne(data)
        solvePartTwo()
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

    private fun solvePartTwo() {
        val hackFromFriends = Day7hack()
        AdventSolver.printAnswer(7, 2, hackFromFriends.part2())
    }
}

data class Directory(
    val name: String = "",
    val parentDirectory: String,
    var subdirectories: MutableList<Directory> = mutableListOf(),
    var totalSize: Int = 0,
)

class Day7hack {
    sealed interface FileSystemNode {
        val name: String
    }

    data class Dir(
        override val name: String, val parent: Dir?,
        var children: List<FileSystemNode>, var totalSize : Long = 0) : FileSystemNode {

        override fun hashCode() = EssentialData(this).hashCode()
        override fun toString() = EssentialData(this).toString()

    }

    data class XFile(override val name: String, val size: Int, val parent: Dir) : FileSystemNode

    private data class EssentialData(val name: String, val totalSize: Long) {
        constructor(dir: Dir) : this(name = dir.name, totalSize = dir.totalSize)
    }

    private val input =
        File("src/main/kotlin/data/day7-data.txt")
            .readLines()

    private val root = Dir("/", null, emptyList())

    private fun crawl(commands: List<String>, currentDir: Dir) {
        if(commands.isEmpty()) return

        val command = commands.first()
        if(command.startsWith("$ cd")) {
            when (command.substring(5)) {
                ".." -> {
                    crawl(commands.drop(1), currentDir.parent!!)
                }
                "/" -> {
                    crawl(commands.drop(1), root)
                }
                else -> {
                    val dirName = command.substring(5)
                    val existingDir = currentDir.children.find { it.name == dirName }
                    val subDir = if(existingDir != null) {
                        existingDir
                    } else {
                        val newDir = Dir(dirName, currentDir, emptyList())
                        currentDir.children = currentDir.children + newDir
                        newDir
                    }
                    crawl(commands.drop(1), subDir as Dir)
                }
            }
        } else if(command.startsWith("$ ls")) {
            val listing = commands.drop(1).takeWhile {
                !it.startsWith("$")
            }

            val filesAndDirs = listing.map {
                val listing = it.split(" ")
                if(listing[0] == "dir") {
                    Dir(listing[1], currentDir, emptyList())
                } else {
                    XFile(listing[1], listing[0].toInt(), currentDir)
                }
            }
            currentDir.children = currentDir.children + filesAndDirs
            crawl(commands.drop(1 + filesAndDirs.size), currentDir)
        }
    }

    private fun calculateTreeSize(currentDir: Dir) : Long {
        currentDir.totalSize = currentDir.children.sumOf {
            when (it) {
                is Dir -> calculateTreeSize(it)
                is XFile -> it.size.toLong()
            }
        }
        return currentDir.totalSize
    }

    init {
        crawl(input, root)
        calculateTreeSize(root)
    }

    private fun filterTreeByTotalSize(currentDir: Dir, filter: (Long) -> Boolean): List<Dir> {
        val childDirs = currentDir.children.flatMap {
            when (it) {
                is Dir -> filterTreeByTotalSize(it, filter)
                else -> emptyList()
            }
        }

        return if(filter(currentDir.totalSize)) {
            childDirs + currentDir
        } else childDirs

    }

    fun part2(): Long {
        val totalSizeOfFS = 70000000L
        val neededSpace = 30000000L

        val sizeToDelete = neededSpace - (totalSizeOfFS - root.totalSize)
        val candidates = filterTreeByTotalSize(root) { size ->
            size > sizeToDelete
        }
        val selection = candidates.minBy { it.totalSize }
        return selection.totalSize
    }
}
