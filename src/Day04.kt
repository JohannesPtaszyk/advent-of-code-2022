fun main() {

    fun part1(input: List<String>): Int = input.map { line ->
        val ranges = line.split(",").map { rangeString ->
            val (first, second) = rangeString.split("-")
            first.toInt()..second.toInt()
        }
        val (first, second) = ranges
        first to second
    }.count {
        val (first, second) = it
        first.intersect(second).size == second.count() || second.intersect(first).size == first.count()
    }

    fun part2(input: List<String>): Int = input.map { line ->
        val ranges = line.split(",").map { rangeString ->
            val (first, second) = rangeString.split("-")
            first.toInt()..second.toInt()
        }
        val (first, second) = ranges
        first to second
    }.count {
        val (first, second) = it
        first.intersect(second).isNotEmpty()
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == 2)
    println("Part 1: ${part1(input)}")

    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == 4)
    println("Part 2: ${part2(input)}")
}
