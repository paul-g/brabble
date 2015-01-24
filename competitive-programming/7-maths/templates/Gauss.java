import java.util.*;
import java.io.*;

class Gauss {

    private static final double EPS = 0.00000001;

    static void swapRows(double m[][], int i, int j) {
        for (int k = 0; k < m[i].length; k++) {
            double t = m[i][k];
            m[i][k] = m[j][k];
            m[j][k] = t;
        }
    }

    static void printMat(double m[][]) {
        for (int i = 0; i < m.length; i++)
            System.out.println(Arrays.toString(m[i]));
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new File("gauss.in"));

        int n = sc.nextInt();
        int m = sc.nextInt();

        double mat[][] = new double[n][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m + 1; j++)
                mat[i][j] = sc.nextDouble();
        }

        // j represents the column (i.e. the variable),
        // i the row (i.e. the equation)
        int i = 0, j = 0;

        // TODO reduce system matrix to row echelon form
        while (i < n && j < m) {

        }

        double x[] = new double[m + 1];
        // TODO rebuild the solution

        System.out.println(Arrays.toString(x));
    }

}
