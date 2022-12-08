const val PATH_DELIMITER = "/"
const val TOTAL_DISK_CAPACITY = 70_000_000
const val NEEDED_SPACE = 30_000_000

data class File(val size: Int, val name: String, val path: String) {
    fun parentDirs(): List<String> = path.removeSuffix(PATH_DELIMITER).let { path ->
        if (path == PATH_DELIMITER) {
            listOf("/")
        } else {
            val segments = path.split(PATH_DELIMITER)
            List(segments.size) { index ->
                segments.subList(0, index + 1).joinToString(separator = PATH_DELIMITER, postfix = PATH_DELIMITER) { it }
            }
        }

    }
}

data class Dir(val path: String, var size: Int)

fun getDirs(input: List<String>): MutableList<Dir> {
    var currentPath = ""
    val dirs = input.asSequence().mapNotNull { line ->
        if (line.startsWith("dir") || line.startsWith("$ ls")) return@mapNotNull null
        if (!line.contains("$")) {
            val (size, name) = line.split(" ")
            return@mapNotNull File(size.toInt(), name, currentPath)
        }
        when (val cd = line.split(" ")[2]) {
            "/" -> currentPath = PATH_DELIMITER
            ".." -> {
                currentPath = currentPath.split(PATH_DELIMITER)
                    .dropLast(2)
                    .joinToString(separator = PATH_DELIMITER, postfix = PATH_DELIMITER) { it }
            }

            else -> currentPath += cd + PATH_DELIMITER

        }
        null
    }.fold(mutableListOf()) { dirs: MutableList<Dir>, file: File ->
        file.parentDirs().forEach { parent ->
            val dir = dirs.find { it.path == parent }
            if (dir == null) {
                dirs.add(Dir(parent, file.size))
            } else {
                dir.size += file.size
            }
        }
        dirs
    }
    return dirs
}



fun main() {

    fun part1(input: List<String>): Int {
        val dirs = getDirs(input)
        return dirs.filter {
            it.size <= 100_000
        }.sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        val dirs = getDirs(input)
        val rootSize = dirs.first { it.path == PATH_DELIMITER }.size
        val neededSpace =  NEEDED_SPACE - (TOTAL_DISK_CAPACITY - rootSize)
        return dirs.sortedBy { it.size }.also { println(it) }.first { it.size > neededSpace }.size
    }

    val testInput = readInput("Day07_test")
    val input = readInput("Day07")

    val part1TestResult = part1(testInput)
    println("Part1 test: $part1TestResult")
    check(part1TestResult == 95437)
    println("Part 1: ${part1(input)}")

    val part2TestResult = part2(testInput)
    println("Part2 test: $part2TestResult")
    check(part2TestResult == 24933642)
    println("Part 2: ${part2(input)}")
}