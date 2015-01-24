import java.io.*;
import java.util.*;

public class Main {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int [][] mat = new int[n][n];
        int [][] sum = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();

                // precompute the sum matrix
                sum[i][j] += mat[i][j] +
                    (i > 0 ? sum[i - 1][j] : 0) +
                    (j > 0 ? sum[i][j - 1] : 0) -
                    (i > 0 && j > 0 ? sum[i - 1][j - 1] : 0);
            }

        dp(mat, sum);

        sc.close();
    }

    // Dynamic programming solution is O(n^4), which is fine for n up to 100.
    static void dp(int [][] mat, int[][] sum) {
        int bestSum = Integer.MIN_VALUE;
        int n = mat.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int li = i; li < n; li++)
                    for (int lj = j; lj < n; lj++) {
                        // use the precomputed sum matrix to reconstruct the sum
                        // of every subsquare in the original matrix
                        int s = sum[li][lj] -
                            (i > 0 ? sum[i - 1][lj] : 0) -
                            (j > 0 ? sum[li][j - 1] : 0) +
                            (i > 0 && j > 0 ? sum[i - 1][j - 1] : 0);
                        bestSum = Math.max(bestSum, s);
                    }

        System.out.println(bestSum);
    }

    // Complete search is too slow O(n^6), for n up to 100.
    static void completeSearch(int [][] mat) {
        int n = mat.length;
        int bestSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int di = i; di < n; di++)
                    for (int dj = j; dj < n; dj++) {
                        int sum = 0;
                        for (int l = i; l <= di; l++)
                            for (int c = j; c <= dj; c++)
                                sum += mat[l][c];
                        bestSum = Math.max(sum, bestSum);
                    }
        System.out.println(bestSum);
    }

}
