import day01.Day01
import day02.Day02
import day03.Day03
import java.io.File

fun main(args: Array<String>) {
    //Testing.run()
    // Day01.run()
    // Day02.run()
    Day03.run()
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    // println("Program arguments: ${args.joinToString()}")
}

object AdventSolver {
    fun read(inputPath: String): MutableList<String> {
        val data = mutableListOf<String>()
        File(inputPath).forEachLine { data.add(it) }
        return data
    }
}

