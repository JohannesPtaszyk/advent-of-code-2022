fun main() {

    val possibleItems: List<Char> = ('a'..'z') + ('A'..'Z')

    fun part1(input: List<String>): Int = input.sumOf {
        val centerIndex = it.length / 2
        val firstCompartment = it.substring(0, centerIndex).toSet()
        val secondCompartment = it.substring(centerIndex, it.length).toSet()
        val sharedItem = firstCompartment.intersect(secondCompartment).first()
        possibleItems.indexOf(sharedItem) + 1
    }


    fun part2(input: List<String>): Int = input.windowed(3, 3).map { rucksacks ->
        rucksacks.fold(mutableSetOf()) { param: MutableSet<Set<Char>>, s: String ->
            val chars = s.toSet()
            param.lastOrNull()?.intersect(chars)?.also {
                param.add(it)
            } ?: param.add(chars)
            param
        }.last()
    }.sumOf {
        possibleItems.indexOf(it.first()) + 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == 157)
    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
