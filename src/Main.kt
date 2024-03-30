operator fun Matrix?.plus(anotherMatrix: Matrix?): Matrix? {
    return this?.add(anotherMatrix)
}

operator fun Matrix?.minus(anotherMatrix: Matrix?): Matrix? {
    return this?.subtract(anotherMatrix)
}

operator fun Matrix?.times(anotherMatrix: Matrix?): Matrix? {
    return this?.multiply(anotherMatrix)
}

operator fun Matrix?.unaryMinus(): Matrix? {
    return this?.negate()
}

fun containsEmptySet(set: AxiomaticSet?): Boolean {
    return set?.contains(AxiomaticSet.EMPTY_SET) ?: false
}

fun main() {
    val mat1: Matrix? = Matrix.createInstance(arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(3, 4, 5)
    ))

    val mat2: Matrix? = Matrix.createInstance(arrayOf(
            intArrayOf(5, 10, 15),
            intArrayOf(100, 500, 1000)
    ))

    val mat3: Matrix? = Matrix.createInstance(arrayOf(
            intArrayOf(3, 6, 9, 12),
            intArrayOf(4, 8, 12, 16),
            intArrayOf(5, 10, 15, 20),
    ))

    val invalidMat: Matrix? = Matrix.createInstance(-2, -4)

    println(mat1 + mat2)
    println(mat1 - mat2)
    println(mat1 * -mat3)
    println(mat2!!.scalarMultiply(50))

    // this produces a NullPointerException.
    println(invalidMat!!.scalarMultiply(10))

    val set1: AxiomaticSet? = AxiomaticSet.setTheoreticN(5)
    val set2: AxiomaticSet? = AxiomaticSet.setTheoreticN(10)
    val invalidSet: AxiomaticSet? = AxiomaticSet.setTheoreticN(-5)
    println(set1)
    println(set2)
    println(invalidSet)
    println(AxiomaticSet.intersection(set1, set2))
}