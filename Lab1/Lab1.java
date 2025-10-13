package Lab1;

public class Lab1 {

    private static long[][] transposeMatrix(long[][] matrixB) {
        if (matrixB == null) {
            throw new IllegalArgumentException("Matrix B cannot be null");
        }

        if (matrixB.length == 0) {
            throw new IllegalArgumentException("Matrix B cannot be empty");
        }

        if (matrixB[0] == null || matrixB[0].length == 0) {
            throw new IllegalArgumentException("Matrix B rows cannot be empty");
        }

        int rows = matrixB.length;
        int cols = matrixB[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrixB[i] == null) {
                throw new IllegalArgumentException("Row " + i + " of matrix B is null");
            }
            if (matrixB[i].length != cols) {
                throw new IllegalArgumentException("All matrix rows must have the same number of columns");
            }
        }

        long[][] matrixC = new long[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrixC[j][i] = matrixB[i][j];
            }
        }

        return matrixC;
    }

    private static long calculateSumOfMinInColumns(long[][] matrixC) {
        if (matrixC == null) {
            throw new IllegalArgumentException("Matrix C cannot be null");
        }

        if (matrixC.length == 0) {
            throw new IllegalArgumentException("Matrix C cannot be empty");
        }

        if (matrixC[0] == null || matrixC[0].length == 0) {
            throw new IllegalArgumentException("Matrix C cannot have empty rows");
        }

        int rows = matrixC.length;
        int cols = matrixC[0].length;
        long sum = 0;

        for (int col = 0; col < cols; col++) {
            long minInColumn = matrixC[0][col];

            for (int row = 1; row < rows; row++) {
                if (matrixC[row][col] < minInColumn) {
                    minInColumn = matrixC[row][col];
                }
            }

            if ((sum > 0 && minInColumn > Long.MAX_VALUE - sum) ||
                (sum < 0 && minInColumn < Long.MIN_VALUE - sum)) {
                throw new ArithmeticException(
                    "Overflow when calculating sum. Current sum: " + sum +
                    ", adding: " + minInColumn
                );
            }

            sum += minInColumn;
            System.out.println("Column " + col + ": minimum element = " + minInColumn);
        }

        return sum;
    }

    private static void printMatrix(long[][] matrix, String name) {
        if (matrix == null || matrix.length == 0) {
            System.out.println(name + " is empty or null");
            return;
        }

        System.out.println("\n" + name + " (" + matrix.length + "x" + matrix[0].length + "):");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%6d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("C5 = 11 % 5 = 1  -> Transpose matrix B");
            System.out.println("C7 = 11 % 7 = 4  -> Element type: long");
            System.out.println("C11 = 11 % 11 = 0 -> Sum of minimum elements in each column");

            long[][] matrixB = {
                {15L, -23L, 8L, 42L},
                {-7L, 31L, -19L, 5L},
                {26L, -11L, 34L, -2L}
            };

            printMatrix(matrixB, "Matrix B (input)");

            System.out.println("\nStep 1: Transpose matrix B");
            long[][] matrixC = transposeMatrix(matrixB);
            printMatrix(matrixC, "Matrix C (transposed)");

            System.out.println("\nStep 2: Calculate sum of minimum elements in each column");
            long result = calculateSumOfMinInColumns(matrixC);

            System.out.println("\nResult: Sum of minimum elements in each column = " + result);

        } catch (IllegalArgumentException e) {
            System.err.println("Input data error: " + e.getMessage());
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
