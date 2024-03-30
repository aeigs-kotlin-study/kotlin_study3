public class Matrix {
    public final int rows;
    public final int columns;
    public final int[][] entries;

    public Matrix(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new InvalidMatrixException();

        this.rows = rows;
        this.columns = columns;
        entries = new int[rows][columns];
    }

    public Matrix(int[][] predefinedEntries) {
        if (predefinedEntries == null || predefinedEntries.length == 0)
            throw new InvalidMatrixException();

        rows = predefinedEntries.length;
        columns = predefinedEntries[0].length;
        entries = new int[rows][columns];

        for (int j = 0; j < rows; j++) {
            if (predefinedEntries[j].length != columns)
                throw new InvalidMatrixException();

            System.arraycopy(predefinedEntries[j], 0, entries[j], 0, columns);
        }
    }

    public double getEntryAt(int row, int column) {
        return entries[row-1][column-1];
    }

    public Matrix negate() {
        Matrix mat = new Matrix(rows, columns);

        for (int j = 0; j < rows; j++)
            for (int k = 0; k < columns; k++)
                mat.entries[j][k] = -entries[j][k];

        return mat;
    }

    public Matrix scalarMultiply(int scalar) {
        Matrix mat = new Matrix(rows, columns);

        for (int j = 0; j < rows; j++)
            for (int k = 0; k < columns; k++)
                mat.entries[j][k] = scalar * entries[j][k];

        return mat;
    }

    public Matrix add(Matrix anotherMatrix) {
        if (rows != anotherMatrix.rows || columns != anotherMatrix.columns)
            throw new InvalidMatrixException();

        Matrix mat = new Matrix(rows, columns);

        for (int j = 0; j < rows; j++)
            for (int k = 0; k < columns; k++)
                mat.entries[j][k] = entries[j][k] + anotherMatrix.entries[j][k];

        return mat;
    }

    public Matrix subtract(Matrix anotherMatrix) {
        return add(anotherMatrix.negate());
    }

    public Matrix multiply(Matrix anotherMatrix) {
        if (columns != anotherMatrix.rows)
            throw new InvalidMatrixException();

        Matrix mat = new Matrix(rows, anotherMatrix.columns);

        for (int j = 0; j < rows; j++)
            for (int k = 0; k < anotherMatrix.columns; k++)
                for (int a = 0; a < columns; a++)
                    mat.entries[j][k] += entries[j][a] * anotherMatrix.entries[a][k];

        return mat;
    }

    public void print() {
        for (int j = 0; j < rows; j++) {
            for (int k = 0; k < columns; k++)
                System.out.print(entries[j][k] + " ");

            System.out.println();
        }
    }
}
