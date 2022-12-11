fun main() {

    fun part1(input: List<String>): Int = input.size

    fun part2(input: List<String>): Int = input.size

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    val testResultPart1 = part1(testInput)
    println("Part1 test: $testResultPart1")
    check(testResultPart1 == 2)
    println("Part 1: ${part1(input)}")

    val testResultPart2 = part2(testInput)
    println("Part2 test: $testResultPart2")
    check(testResultPart2 == 0)
    println("Part 2: ${part2(input)}")
}
