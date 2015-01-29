import java.util.Random;

public class Measuring {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Measuring <matrix_size>\n");
            return;
        }

        int size = Integer.parseInt(args[0]);
        System.out.print(size + " ");
        Matrix a = new Matrix(size);
        a.fill_random();
        Matrix b = new Matrix(size);
        b.fill_random();

        long start = System.currentTimeMillis();
        Matrix c1 = a.multiply_faster(b);
        long fast_time_ms = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        Matrix c2 = a.multiply(b);
        long slow_time_ms = System.currentTimeMillis() - start;

        System.out.format("%d %d %f\n", fast_time_ms, slow_time_ms, slow_time_ms/(double)fast_time_ms);
    }
    
}


class Matrix {
    private double[][] data;
    private final int size;

    Matrix(int size) {
        this.size = size;
        data = new double[size][size];
    }
    
    Matrix multiply(Matrix other) {
        Matrix c = new Matrix(size);
        int n  = size;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    c.data[i][j] += data[i][k] * other.data[k][j];
        return c;
    }

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
    void fill_random() {
        Random r = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                data[i][j] = r.nextDouble();
    }

    void print () {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.format("%.2f ", data[i][j]);
            System.out.println();
        }
    }

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

// class Filter { 

//     private double data[];

//     void filter() {
//     }
// }
