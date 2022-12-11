fun main() {

    fun getCycles(input: List<String>) = input.flatMap {
        val instruction = it.split(" ")
        if (instruction.first() == "noop") {
            listOf(null)
        } else {
            listOf(null, instruction[1].toInt())
        }
    }

    fun part1(input: List<String>): Int {
        var x = 1
        var result = 0

        val measuredCycles = IntProgression.fromClosedRange(20,220,40)
        getCycles(input).forEachIndexed { index, instruction ->
            val cycleNo = index + 1
            if(measuredCycles.contains(cycleNo)){
                val signalStrength = x * cycleNo
                result += signalStrength
            }
            if(instruction != null) {
                x += instruction
            }
        }

        return result
    }

    fun part2(input: List<String>): String  {
        var x = 1
        var result = ""
        getCycles(input).chunked(40).forEach {
            it.forEachIndexed { index, instruction ->
                result += if(index in x-1..x+1) "#" else "."
                if(result.replace(System.lineSeparator(), "").length % 40 == 0) {
                    result+=System.lineSeparator()
                }
                if(instruction != null) {
                    x += instruction
                }
            }
        }
        return result
    }

    val testInput = readInput("Day10_test")
    val input = readInput("Day10")

    val testResultPart1 = part1(testInput)
    println("Part1 test: $testResultPart1")
    check(testResultPart1 == 13140)
    println("Part 1: ${part1(input)}")

    val testResultPart2 = part2(testInput)
    println("Part2 test: ${System.lineSeparator()}$testResultPart2")
    println("Part 2: ${System.lineSeparator()}${part2(input)}")
}
