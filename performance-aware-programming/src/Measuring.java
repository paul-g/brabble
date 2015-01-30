import java.util.Random;
import java.util.function.*;
import java.util.Arrays;

public class Measuring {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Measuring <matrix_size> [-m|-f]\n");
            return;
        }
        int size = Integer.parseInt(args[0]);
        System.out.print(size + " ");

        if (args[1].equals("-m"))
            matrixMultiplyExperiment(size);
        else
            filterSortingExperiment(size * 1024);
    }

    public static void matrixMultiplyExperiment(int size) {
        Matrix a = (new Matrix(size)).fillRandom();
        Matrix b = (new Matrix(size)).fillRandom();
        time(a::multiply, a::multiply_faster, b);
    }

    public static void filterSortingExperiment(int size) {
        Filter slowf = (new Filter(size)).fillRandom();
        Filter fastf = (new Filter(size)).fillRandom().sort();
        time(slowf::filterSum, fastf::filterSum, 128l);
    }

    public static <T, R> void time(Function<T, R> slow,
                                   Function<T, R> fast,
                                   T other) {

        long start = System.currentTimeMillis();
        R r1 = slow.apply(other);
        long mid = System.currentTimeMillis();
        R r2 = fast.apply(other);
        long end = System.currentTimeMillis();

        long slow_time_ms = mid - start;
        long fast_time_ms = end - mid;

        double speedup = 0;
        if (fast_time_ms != 0)
            speedup = (double)slow_time_ms / fast_time_ms;

        System.out.format("%d %d %f\n", fast_time_ms, slow_time_ms, speedup);
        if (!r1.equals(r2))
            throw new RuntimeException("Results differ");
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
    Matrix multiply_faster(Matrix other) {
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


/**
   An example of how much branch prediction can influence the
   performance - the sorted array is a few times faster to filter
   than the unsorted one. Overall, this approach does not result in a
   speedup since the sorting time dominates.

   This example is based on:
   http://stackoverflow.com/questions/11227809/why-is-processing-a-sorted-array-faster-than-an-unsorted-array
*/
class Filter {

    private long data[];

    Filter(int size) {
        this.data = new long[size];
    }

    Filter fillRandom() {
        Random r = new Random();
        for (int i = 0; i < data.length; i++)
            data[i] = r.nextLong() % 100;
        return this;
    }

    /** Sums elements larger than limit. */
    long filterSum(long limit) {
        long sum = 0;
        for (long a : data)
            if (a > limit)
                sum += a;
        return sum;
    }

    Filter sort() {
        Arrays.sort(data);
        return this;
    }
}
