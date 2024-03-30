operator fun Matrix.plus(anotherMatrix: Matrix): Matrix {
    return add(anotherMatrix)
}

operator fun Matrix.minus(anotherMatrix: Matrix): Matrix {
    return subtract(anotherMatrix)
}

operator fun Matrix.times(anotherMatrix: Matrix): Matrix {
    return multiply(anotherMatrix)
}

operator fun Matrix.unaryMinus(): Matrix {
    return negate()
}

fun main() {
    val mat1 = Matrix(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(3, 4, 5)
    ))

    val mat2 = Matrix(arrayOf(
            intArrayOf(5, 10, 15),
            intArrayOf(100, 500, 1000)
    ))

    val mat3 = Matrix(arrayOf(
            intArrayOf(3, 6, 9, 12),
            intArrayOf(4, 8, 12, 16),
            intArrayOf(5, 10, 15, 20),
    ))



    val addition = mat1 + mat2
    val subtraction = mat1 - mat2
    val multiplication = mat1 * mat3
    val negatedMultiplication = mat1 * -mat3

    addition.print()
    println()
    subtraction.print()
    println()
    multiplication.print()
    println()
    negatedMultiplication.print()
}