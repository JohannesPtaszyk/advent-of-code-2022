fun main() {

    fun part1(input: List<String>): Int = input.size

    fun part2(input: List<String>): Int = input.size

    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == 2)
    println("Part 1: ${part1(input)}")

    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == 0)
    println("Part 2: ${part2(input)}")
}
