import kotlin.math.absoluteValue

private fun Matrix.validateIndices(row: Int = 1, column: Int = 1) {
    if (row !in 1..rows || column !in 1..columns)
        throw IllegalArgumentException()

}

private fun Matrix.setEntryAt(row: Int, column: Int, value: Double) {
    validateIndices(row, column)
    entries[row-1][column-1] = value
}

private fun Matrix.getEntryAt(row: Int, column: Int): Double {
    validateIndices(row, column)
    return entries[row-1][column-1]
}

fun Matrix.deepCopy() = Matrix(entries)

fun Matrix.negate(): Matrix {
    val result = Matrix(rows, columns)
    for (j in 1..rows)
        for (k in 1..columns)
            result.setEntryAt(j, k, -getEntryAt(j, k))

    return result
}

fun Matrix.scalarMultiply(scalar: Double): Matrix {
    val result = Matrix(rows, columns)
    for (j in 1..rows)
        for (k in 1..columns)
            result.setEntryAt(j, k, getEntryAt(j, k) * scalar)

    return result
}

private fun Matrix.isAddable(mat: Matrix) = rows == mat.rows && columns == mat.columns

private fun Matrix.isMultipliable(mat: Matrix) = columns == mat.rows

fun Matrix.add(mat: Matrix): Matrix {
    if (!isAddable(mat))
        throw IllegalArgumentException("unmatched matrix sizes")

    val result = Matrix(rows, columns)
    for (j in 1..rows)
        for (k in 1..columns)
            result.setEntryAt(j, k, getEntryAt(j, k) + mat.getEntryAt(j, k))

    return result
}

fun Matrix.subtract(mat: Matrix) = if (isAddable(mat)) add(mat.negate()) else throw IllegalArgumentException("unmatched matrix sizes")

fun Matrix.multiply(mat: Matrix): Matrix {
    if (!isMultipliable(mat))
        throw IllegalArgumentException("unmatched matrix sizes")

    val result = Matrix(rows, mat.columns)
    for (j in 1..rows)
        for (k in 1..mat.columns)
            for (a in 1..columns)
                result.setEntryAt(j, k, result.getEntryAt(j, k) + getEntryAt(j, a) * mat.getEntryAt(a, k))

    return result
}

// overloading basic operators: +, -, *
operator fun Matrix.plus(mat: Matrix) = add(mat)
operator fun Matrix.minus(mat: Matrix) = subtract(mat)
operator fun Matrix.times(mat: Matrix) = multiply(mat)
operator fun Matrix.unaryMinus() = negate()

private fun Matrix.swapRows(row1: Int, row2: Int) {
    validateIndices(row = row1)
    validateIndices(row = row2)

    val temp = entries[row1 - 1]
    entries[row1 - 1] = entries[row2 - 1]
    entries[row2 - 1] = temp
}

private fun Matrix.pivotableRow(startingRow: Int, pivot: Int): Int {
    for (j in startingRow..rows)
        if (entries[j-1][pivot-1] != 0.0)
            return j

    return 0
}

private fun Matrix.subtractRow(row: Int, targetRow: Int, scalar: Double) {
    for (k in 1..columns)
        entries[targetRow-1][k-1] -= entries[row-1][k-1] * scalar
}

private fun Matrix.reduceFrom(pivotRow: Int, pivot: Int) {
    for (j in (pivotRow+1)..rows)
        subtractRow(pivotRow, j, entries[j-1][pivot-1]/entries[pivotRow-1][pivot-1])
}

fun Matrix.rowEchelon(): Matrix {
    val result = deepCopy()

    var pivot = 0
    var pivotRow: Int

    for (j in 1..<rows) {
        if (++pivot > columns)
            break

        pivotRow = result.pivotableRow(j, pivot)
        if (pivotRow != 0)
            result.swapRows(j, pivotRow)
        else
            continue

        result.reduceFrom(j, pivot)
    }

    return result
}

private fun isAlmostZero(number: Double) = number.absoluteValue < 10E-10

private fun Matrix.isZeroRow(row: Int): Boolean {
    for (k in 1..columns)
        if (!isAlmostZero(entries[row-1][k-1]))
            return false

    return true;
}

fun Matrix.rank(): Int {
    var nonZeroRowCount: Int = 0
    val rowEchelonMat = rowEchelon()

    for (j in 1..rows)
        if (!rowEchelonMat.isZeroRow(j)) nonZeroRowCount++

    return nonZeroRowCount
}

fun Matrix.nullity() = columns - rank()