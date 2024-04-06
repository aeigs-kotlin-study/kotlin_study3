import java.util.Arrays;

public class Matrix {
    public final int rows;
    public final int columns;
    public final double[][] entries;

    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException();

        this.rows = rows;
        this.columns = columns;
        entries = new double[rows][columns];
    }

    public Matrix(double[][] predefinedEntries) {
        if (predefinedEntries.length == 0 || predefinedEntries[0].length == 0)
            throw new IllegalArgumentException();

        rows = predefinedEntries.length;
        columns = predefinedEntries[0].length;
        entries = new double[rows][columns];

        for (int j = 0; j < rows; j++) {
            if (predefinedEntries[j].length != columns)
                throw new IllegalArgumentException();

            System.arraycopy(predefinedEntries[j], 0, entries[j], 0, columns);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++)
            builder.append(Arrays.toString(entries[i])).append('\n');

        return builder.toString();
    }
}
