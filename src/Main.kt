fun IntArray.toDoubleArray(): DoubleArray {
    val conversion = DoubleArray(size)
    for (i in indices)
        conversion[i] = elementAt(i).toDouble()

    return conversion
}

fun main() {
    val mat1 = Matrix(arrayOf(
            intArrayOf(1, 2, 3).toDoubleArray(),
            intArrayOf(3, 4, 5).toDoubleArray()
    ))

    val mat2 = Matrix(arrayOf(
            intArrayOf(5, 10, 15).toDoubleArray(),
            intArrayOf(100, 500, 1000).toDoubleArray()
    ))

    val mat3 = Matrix(arrayOf(
            intArrayOf(3, 6, 9, 12).toDoubleArray(),
            intArrayOf(4, 10, 12, 16).toDoubleArray(),
            intArrayOf(5, 10000, 150, 20).toDoubleArray(),
    ))

    // ChatGPT-generated 10x12 matrix.
    val matLarge = Matrix(arrayOf(
            intArrayOf(51, 92, 14, 71, 60, 20, 82, 86, 74, 74, 87, 99).toDoubleArray(),
            intArrayOf(102, 184, 28, 142, 120, 40, 164, 172, 148, 148, 174, 198).toDoubleArray(),
            intArrayOf(51, 92, 14, 71, 60, 20, 82, 86, 74, 74, 87, 99).toDoubleArray(),
            intArrayOf(14, 61, 61, 46, 61, 50, 54, 63, 2, 100, 50, 6).toDoubleArray(),
            intArrayOf(20, 72, 38, 17, 3, 88, 59, 13, 8, 89, 52, 1).toDoubleArray(),
            intArrayOf(83, 91, 59, 70, 43, 7, 46, 34, 77, 80, 35, 49).toDoubleArray(),
            intArrayOf(3, 1, 5, 53, 3, 53, 92, 62, 17, 89, 43, 33).toDoubleArray(),
            intArrayOf(73, 61, 99, 13, 94, 47, 14, 71, 77, 86, 61, 39).toDoubleArray(),
            intArrayOf(84, 79, 81, 52, 23, 25, 88, 59, 40, 28, 14, 44).toDoubleArray(),
            intArrayOf(64, 88, 70, 8, 87, 0, 7, 87, 62, 10, 80, 7).toDoubleArray()
    ))

    println(matLarge.rowEchelon())
    println("The rank of the given matrix is equal to ${matLarge.rank()}.")
    println("The nullity of the given matrix is equal to ${matLarge.nullity()}.")
}