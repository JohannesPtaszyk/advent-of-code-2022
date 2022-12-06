fun main() {

    fun List<String>.findMarkerWithLength(markerLength: Int) = map { line ->
        var marker = ""
        for (i in 0..line.length) {
            val markerCandidate = line.substring(i, i + markerLength)
            val chars = markerCandidate.toList().distinct()
            val hasDuplicates = chars.size < markerLength
            if(!hasDuplicates) {
                marker = markerCandidate
                break
            }
        }
        line.indexOf(marker) + markerLength
    }.first()

    fun part1(input: List<String>): Int = input.findMarkerWithLength(4)

    fun part2(input: List<String>): Int = input.findMarkerWithLength(14)

    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == 7)
    println("Part 1: ${part1(input)}")

    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == 19)
    println("Part 2: ${part2(input)}")
}
