fun main() {

    class Rope(knotCount: Int) {
        val knots: List<MutableList<Pair<Int, Int>>> = buildList {
            repeat(knotCount) { add(mutableListOf(Pair(0, 0))) }
        }

        fun getTailPositions(): Int = knots.last().distinctBy { it }.count()

        fun move(input: List<String>) {
            input.forEach {
                val (direction, moves) = it.split(" ")
                repeat(moves.toInt()) {
                    val head = knots.first()
                    var (hx, hy) = head.last()
                    when (direction) {
                        "U" -> hy += 1
                        "D" -> hy -= 1
                        "R" -> hx += 1
                        "L" -> hx -= 1
                        else -> throw UnsupportedOperationException("$direction is not a valid move")
                    }
                    head.add(hx to hy)

                    knots.drop(1).forEachIndexed { index: Int, knot: MutableList<Pair<Int, Int>> ->
                        val (px, py) = knots[index].last()
                        var (kx, ky) = knot.last()
                        when {
                            //Up
                            kx == px && py - ky == 2 -> {
                                ky += 1
                            }
                            //Down
                            kx == px && ky - py == 2 -> {
                                ky -= 1
                            }
                            //Right
                            ky == py && px - kx == 2 -> {
                                kx += 1
                            }
                            //Left
                            ky == py && kx - px == 2 -> {
                                kx -= 1
                            }
                            //Top-right
                            (px - kx >= 1 && py - ky == 2) || (px - kx == 2 && py - ky >= 1) -> {
                                ky += 1
                                kx += 1
                            }
                            //Top-Left
                            (kx - px >= 1 && py - ky == 2) || (kx - px == 2 && py - ky >= 1) -> {
                                ky += 1
                                kx -= 1
                            }
                            //Bottom-right
                            (px - kx >= 1 && ky - py == 2) || (px - kx == 2 && ky - py >= 1) -> {
                                ky -= 1
                                kx += 1
                            }
                            //Bottom-left
                            (kx - px >= 1 && ky - py == 2) || (kx - px == 2 && ky - py >= 1) -> {
                                ky -= 1
                                kx -= 1
                            }
                        }
                        knot.add(kx to ky)
                    }
                }
            }
        }
    }


    fun part1(input: List<String>): Int {
        val rope = Rope(2)
        rope.move(input)
        return rope.getTailPositions()
    }

    fun part2(input: List<String>): Int {
        val rope = Rope(10)
        rope.move(input)
        return rope.getTailPositions()
    }

    val testInput = readInput("Day09_test")
    val input = readInput("Day09")

    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == 88)
    println("Part 1: ${part1(input)}")

    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == 36)
    println("Part 2: ${part2(input)}")
}
