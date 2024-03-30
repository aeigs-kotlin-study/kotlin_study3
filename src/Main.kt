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

fun containsEmptySet(set: AxiomaticSet?): Boolean {
    return set?.contains(AxiomaticSet.EMPTY_SET) ?: false
}

fun main() {
    val mat1 = Matrix.createInstance(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(3, 4, 5)
    ))

    val mat2 = Matrix.createInstance(arrayOf(
            intArrayOf(5, 10, 15),
            intArrayOf(100, 500, 1000)
    ))

    val mat3 = Matrix.createInstance(arrayOf(
            intArrayOf(3, 6, 9, 12),
            intArrayOf(4, 8, 12, 16),
            intArrayOf(5, 10, 15, 20),
    ))

    val addition = mat1 + mat2
    val subtraction = mat1 - mat2
    val negatedMultiplication = mat1 * -mat3
    val scalarMultiplication = mat2.scalarMultiply(50)

    println(addition)
    println(subtraction)
    println(negatedMultiplication)
    println(scalarMultiplication)

    println(AxiomaticSet.setTheoreticN(10))
    println(containsEmptySet(AxiomaticSet.setTheoreticN(5)))
}