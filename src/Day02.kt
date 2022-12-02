enum class Result(val id: String, val score: Int) {
    LOOSE(id = "X", score = 0),
    DRAW(id = "Y", score = 3),
    WIN(id = "Z", score = 6);

    companion object {
        fun forId(id: String): Result = values().first {it.id == id }
    }
}

enum class Shape(val score: Int, val encryptedValues: List<String>) {
    ROCK(score = 1, encryptedValues = listOf("A", "X")),
    PAPER(score = 2, encryptedValues = listOf("B", "Y")),
    SCISSORS(score = 3, encryptedValues = listOf("C", "Z"));

    fun playVs(opponent: Shape): Result {
        if (this == opponent) return Result.DRAW
        val isWin = when (this) {
            ROCK -> opponent == SCISSORS
            PAPER -> opponent == ROCK
            SCISSORS -> opponent == PAPER
        }
        return if (isWin) Result.WIN else Result.LOOSE
    }

    fun getOpponentForResult(expectedResult: Result): Shape = values().first {
        it.playVs(this) == expectedResult
    }

    companion object {
        fun forEncryptedValue(encryptedValue: String): Shape = values().first {
            it.encryptedValues.contains(encryptedValue)
        }
    }
}


fun main() {

    fun part1(input: List<String>): Int = input.sumOf {
        val (opponentValue, myValue) = it.split(" ")
        val opponentShape = Shape.forEncryptedValue(opponentValue)
        val myShape = Shape.forEncryptedValue(myValue)
        myShape.playVs(opponentShape).score + myShape.score
    }

    fun part2(input: List<String>): Int = input.sumOf {
        val (opponentValue, expectedResultId) = it.split(" ")
        val opponentShape = Shape.forEncryptedValue(opponentValue)
        val expectedResult = Result.forId(expectedResultId)
        val myShape = opponentShape.getOpponentForResult(expectedResult)
        myShape.score + expectedResult.score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
