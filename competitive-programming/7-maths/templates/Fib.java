import java.util.*;

class Fib {

    private static final int MOD = 666013;

    private static final long[][] orig = {
        {0, 1},
        {1, 1}
    };

    /** Using recurrence relation - really slow! */
    static int fib(int n) {
        return 0;
    }

    /** Using memoization */
    static long fib2(long n) {
        return 0;
    }

    /** Using fast exponentation */
    static long fib3(long n) {
        // use fast exponentation and matrix formula
        long[][] a = {
            {0, 1},
            {1, 1}
        };
        mat_pow(a, n);
        return a[0][1];
    }

    static void mult(long[][] a, long[][] b) {
        int n = 2;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(c[i], 0);

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    c[i][j] = (c[i][j] + (a[i][k] * b[k][j]) % MOD) % MOD;

        for (int i = 0; i < n; i++)
            System.arraycopy(c[i], 0, a[i], 0, n);
    }

    static void mat_pow(long[][] a, long n) {
    }


    public static void main(String[] args) {
        long n = 10000000;

        long start = System.currentTimeMillis();
        System.out.println(fib2(n));
        System.out.println("fib2 took " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(fib3(n));
        System.out.println("fib3 took " + (System.currentTimeMillis() - start));

    }
}
