import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Matrix {
    public final int rows;
    public final int columns;
    public final int[][] entries;

    private Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        entries = new int[rows][columns];
    }

    private Matrix(int[][] predefinedEntries) {
        rows = predefinedEntries.length;
        columns = predefinedEntries[0].length;
        entries = new int[rows][columns];

        for (int j = 0; j < rows; j++) {
            System.arraycopy(predefinedEntries[j], 0, entries[j], 0, columns);
        }
    }

    @Nullable
    public static Matrix createInstance(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            return null;

        return new Matrix(rows, columns);
    }

    @Nullable
    public static Matrix createInstance(int[][] predefinedEntries) {
        if (predefinedEntries == null)
            return null;

        int rows = predefinedEntries.length;
        int columns = predefinedEntries[0].length;

        if (rows == 0 || columns == 0)
            return null;

        for (int i = 1; i < predefinedEntries.length; i++)
            if (columns != predefinedEntries[i].length)
                return null;

        return new Matrix(predefinedEntries);
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

    @Nullable
    public Matrix add(Matrix anotherMatrix) {
        if (anotherMatrix == null)
            return null;

        if (rows != anotherMatrix.rows || columns != anotherMatrix.columns)
            return null;

        Matrix mat = new Matrix(rows, columns);

        for (int j = 0; j < rows; j++)
            for (int k = 0; k < columns; k++)
                mat.entries[j][k] = entries[j][k] + anotherMatrix.entries[j][k];

        return mat;
    }

    @Nullable
    public Matrix subtract(Matrix anotherMatrix) {
        if (anotherMatrix == null)
            return null;

        return add(anotherMatrix.negate());
    }

    @Nullable
    public Matrix multiply(Matrix anotherMatrix) {
        if (anotherMatrix == null)
            return null;

        if (columns != anotherMatrix.rows)
            return null;

        Matrix mat = new Matrix(rows, anotherMatrix.columns);

        for (int j = 0; j < rows; j++)
            for (int k = 0; k < anotherMatrix.columns; k++)
                for (int a = 0; a < columns; a++)
                    mat.entries[j][k] += entries[j][a] * anotherMatrix.entries[a][k];

        return mat;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(entries);
    }
}
