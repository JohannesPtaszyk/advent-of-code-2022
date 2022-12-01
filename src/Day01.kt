fun main() {

    fun getElvesCalories(input: List<String>) =
        input.fold(mutableListOf()) { elves: MutableList<Int>, s: String ->
            elves.apply {
                when {
                    isEmpty() || s.isBlank() -> add(0)
                    s.isNotBlank() -> this[lastIndex] += s.toInt()
                }
            }
        }

    fun part1(input: List<String>): Int {
        return getElvesCalories(input).max()
    }

    fun part2(input: List<String>): Int {
        return getElvesCalories(input).sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 4)
    check(part2(testInput) == 9)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
