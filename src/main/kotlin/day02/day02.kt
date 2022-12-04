package day02

import java.io.File

object Day02 {
    fun run() {
        val data = mutableListOf<String>()
        File("src/main/kotlin/day02/day02-data.txt").forEachLine { data.add(it) }
        var pointsByShape = 0
        var pointsByResult = 0
        data.forEach { pointsByShape += pointsFromMatchByShape(it.first(), it.last()) }
        println("Part 1: win with $pointsByShape points") // 11449
        data.forEach { pointsByResult += pointsFromMatchByResult(it.first(), it.last()) }
        println("Part 2: win with $pointsByResult points") // 13187
    }

    fun pointsFromMatchByShape(opponent: Char, self: Char): Int {
        if (self == 'X') {
            if (opponent == 'A') return 4
            if (opponent == 'B') return 1
            return 7
        }
        if (self == 'Y') {
            if (opponent == 'A') return 8
            if (opponent == 'B') return 5
            return 2
        }
        if (opponent == 'A') return 3
        if (opponent == 'B') return 9
        return 6
    }

    fun pointsFromMatchByResult(opponent: Char, self: Char): Int {
        if (opponent == 'A') {
            if (self == 'X') return 3
            if (self == 'Y') return 4
            return 8
        }
        if (opponent == 'B') {
            if (self == 'X') return 1
            if (self == 'Y') return 5
            return 9
        }
        if (self == 'X') return 2
        if (self == 'Y') return 6
        return 7
    }
}