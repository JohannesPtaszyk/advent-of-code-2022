import kotlin.math.min

fun main() {

    data class MoveInstruction(val amount: Int, val fromIndex: Int, val toIndex: Int)

    fun getStacks(stackInput: List<String>): MutableList<MutableList<String>> =
        stackInput.fold(mutableListOf()) { stacks: MutableList<MutableList<String>>, line: String ->
            line.windowed(3, 4).forEachIndexed { index, stackValue ->
                val currentStack = stacks.getOrElse(index) { mutableListOf() }
                if(stackValue.isNotBlank()) {
                    currentStack.add(stackValue.removeSurrounding("[", "]"))
                }
                if (stacks.getOrNull(index) == null) {
                    stacks.add(currentStack)
                } else {
                    stacks[index] = currentStack
                }
            }
            stacks
        }

    fun getInstructions(
        input: List<String>,
        fileSeparatorIndex: Int
    ) = input.subList(fileSeparatorIndex + 1, input.size).map { instruction ->
        val (amount, from, to) = Regex("[0-9]+").findAll(instruction).map { it.value.toInt() }.toList()
        MoveInstruction(amount, from - 1, to - 1)
    }

    fun getStacksAndInstructions(input: List<String>): Pair<MutableList<MutableList<String>>, List<MoveInstruction>> {
        val fileSeparatorIndex = input.indexOfFirst { it.isBlank() }
        val stackInput = input.subList(0, fileSeparatorIndex - 1)
        val stacks = getStacks(stackInput)
        val instructions = getInstructions(input, fileSeparatorIndex)
        return Pair(stacks, instructions)
    }

    fun MutableList<MutableList<String>>.joinToResult() = map { it.first() }.joinToString(separator = "") { it }

    fun part1(input: List<String>): String {
        val (stacks, instructions) = getStacksAndInstructions(input)
        instructions.forEach { instruction ->
            repeat(instruction.amount) {
                val from = stacks[instruction.fromIndex]
                val movable = from.removeFirst()
                stacks[instruction.toIndex].add(0, movable)
            }
        }
        return stacks.joinToResult()
    }

    fun part2(input: List<String>): String {
        val (stacks, instructions) = getStacksAndInstructions(input)
        instructions.forEach { instruction ->
                val from = stacks[instruction.fromIndex]
                val movables = from.subList(0, min(instruction.amount, from.size)).toList()
                repeat(instruction.amount) {
                    from.removeFirst()
                }
                stacks[instruction.toIndex].addAll(0, movables)
        }
        return stacks.joinToResult()
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    println("Part1 test: ${part1(testInput)}")
    check(part1(testInput) == "CMZ")
    println("Part 1: ${part1(input)}")

    println("Part2 test: ${part2(testInput)}")
    check(part2(testInput) == "MCD")
    println("Part 2: ${part2(input)}")
}
