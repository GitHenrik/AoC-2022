import java.io.File

object Testing {
    fun run(): Unit {
        val data = mutableListOf<String>()
        File("src/main/kotlin/day02-data.txt").forEachLine { data.add(it) }
        val sanitizedData: List<String> = data.filter { it.toIntOrNull() != null }
        val sumOfData: Int = sanitizedData.sumOf { it.toInt() }

        // edit "result" reference to print current result
        val result = sumOfData
        println(result)
    }
}
