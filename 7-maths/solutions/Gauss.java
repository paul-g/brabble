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

        int i = 0, j = 0;
        // j represents the column, i the row
        while (i < n && j < m) {

            // find a non-zero in the current column
            int k;
            for (k = i; k < n; k++)
                if (Math.abs(mat[k][j]) > EPS)
                    break;

            if (k == n) {
                j++;
                continue;
            }

            // swap this row with the current i
            if (i != k)
                swapRows(mat, i, k);

            // divide row by pivot
            for (int kk = j + 1; kk < m + 1; kk++)
                mat[i][kk] /= mat[i][j];
            mat[i][j] = 1;

            // update all rows below the pivot row
            for (int kk = i + 1; kk < n; kk++) {
                for (int l = j + 1; l < m + 1; l++)
                    mat[kk][l] -= mat[kk][j] * mat[i][l];
                mat[kk][j] = 0;
            }

            ++i;
            ++j;
        }

        double x[] = new double[m + 1];
        // rebuild the solution
        for (i = n - 1; i >= 0; i--) {
            for (j = 0; j < m; j++) {
                if (Math.abs(mat[i][j]) < EPS) continue;
                x[j] = mat[i][m];
                for (int k = j + 1; k < m; k++)
                    x[j] -= x[k] * mat[i][k];
                break;
            }
        }

        System.out.println(Arrays.toString(x));
    }

}
