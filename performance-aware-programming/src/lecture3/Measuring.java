import java.util.Random;
import java.util.Arrays;

public class Measuring {

    /** Compare loop interchanged versus naive matrix multiply.
     *
     * Usage: java Measuring <matrix_size>
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Measuring <matrix_size> <algorithm>\n");
            return;
        }
        int size = Integer.parseInt(args[0]);
        System.out.print(size + " ");

        String algorithm = args[1];

        Matrix a = (new Matrix(size)).fillRandom();
        Matrix b = (new Matrix(size)).fillRandom();
        if (algorithm.equals("-s"))
            a.multiply(b);
        else if (algorithm.equals("-f"))
            a.multiplyFaster(b);
        else 
            System.out.println("Unknown option " + algorithm);
    }

}

/** A vanilla matrix implementation. */
class Matrix {

    private double[][] data;
    private final int size;

    Matrix(int size) {
        this.size = size;
        data = new double[size][size];
    }

    /** Naive multiplication with another matrix. */
    Matrix multiply(Matrix other) {
        Matrix c = new Matrix(size);
        int n  = size;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    c.data[i][j] += data[i][k] * other.data[k][j];
        return c;
    }

    /** Loop interchanged matrix multiply - faster for larger matrices */
    Matrix multiplyFaster(Matrix other) {
        Matrix c = new Matrix(size);
        int n  = size;
        for (int i = 0; i < n; i++)
            for (int k = 0; k < n; k++)
                for (int j = 0; j < n; j++)
                    c.data[i][j] += data[i][k] * other.data[k][j];
        return c;
    }

    /** Fills this matrix with random data */
    Matrix fillRandom() {
        Random r = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                data[i][j] = r.nextDouble();
        return this;
    }

    /** Prints the matrix values */
    void print () {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.format("%.2f ", data[i][j]);
            System.out.println();
        }
    }

    /** Check if matrix equal other */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Matrix) | other == null)
            return false;

        Matrix m = (Matrix)other;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (m.data[i][j] != data[i][j])
                    return false;

        return true;
    }
}
