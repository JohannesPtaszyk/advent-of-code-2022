fun main() {
    
    fun String.toRow() = toList().map { it.digitToInt() }

    fun List<List<Int>>.getColumn(x: Int) = map { it[x] }

    fun part1(input: List<String>): Int {
        val heightMap = input.map { it.toRow() }
        return heightMap.mapIndexed { y, row ->
            row.mapIndexed { x, height ->
                val column = heightMap.getColumn(x)
                row.subList(0, x).all { it < height }
                        || row.subList(x + 1, row.size).all { it < height }
                        || column.subList(0, y).all { it < height }
                        || column.subList(y + 1, column.size).all { it < height }
            }.count { it }
        }.sum()
    }

    fun List<Int>.distanceToHigherOrEvenTree(height: Int): Int {
        forEachIndexed { index: Int, tree: Int -> if (tree >= height) return index + 1 }
        return size
    }

    fun part2(input: List<String>): Int {
        val heightMap = input.map { it.toRow() }
        return heightMap.mapIndexed { y, row ->
            row.mapIndexed { x, height ->
                val column = heightMap.getColumn(x)
                val left = row.subList(0, x).asReversed().distanceToHigherOrEvenTree(height)
                val right = row.subList(x + 1, row.size).distanceToHigherOrEvenTree(height)
                val top = column.subList(0, y).asReversed().distanceToHigherOrEvenTree(height)
                val bottom = column.subList(y + 1, column.size).distanceToHigherOrEvenTree(height)
                println("($x,$y) Height:$height L:$left, T:$top, R:$right, B:$bottom")
                left * right * top * bottom
            }.max()
        }.max()
    }

    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == 21)
    println("Part 1: ${part1(input)}")

    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == 8)
    println("Part 2: ${part2(input)}")
}
