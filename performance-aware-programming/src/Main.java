import java.util.*;

import static java.lang.Math.*;


class KnapsackSolver {

    private int w[], p[];
    private double memo[][];

    private int cacheHits, unique, calls;

    class KnapsackSolver(int w[], int p[]) {
        this.w = w;
        this.p = p;
    }

    private double solve2() {
    }

    double solve() {
        this.calls = 0;
        this.cacheHits = 0;
        this.unique = 0
        long start = System.currentTimeMillis();
        solve2();
        long tookMs = System.currentTimeMillis() - start;
        System.out.println(tookMs);
        System.out.println(calls);
        System.out.println(cacheHits);
        System.out.println(unique);
    }
}

class CompleteSearchSolver extends KnapsackSolver {
    @Override
    double solve(int i, weight) {
        // performance
        System.out.println(i + " " + weight);
        this.calls++;

        if (i == 0)
            return 0;
        if (w[i] <= weight)
            return max(profit(i - 1, weight),
                       profit(i - 1, weight - w[i]) + p[i]);
        return profit(i - 1, weight);
    }
}

class CompleteSearchPrunedSolver extends KnapsackSolver {
    @Override
    double solve(int i, weight) {
        // performance
        System.out.println(i + " " + weight);
        this.calls++;

        if (i == 0 || weight == 0)
            return 0;
        if (w[i] <= weight)
            return max(profit(i - 1, weight),
                       profit(i - 1, weight - w[i]) + p[i]);
        return profit(i - 1, weight);
    }
}

class MemoSolver extends KnapsackSolver {
    
    private double memo[][];

    public MemoSolver(int w[], int p[]) {
        super(w, p);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < maxWeight + 1; j++)
                memo[i][j] = -1;
    }

    public static double memo(int i, int weight) {
        if (memo[i][weight] == -1)
            memo[i][weight] = profitMemo(i, weight);
        return memo[i][weight];
    }

    // the recurrence is the same, but we cache the results in memo to
    // avoid unnecessary computation
    @Override
    double solve (int i, int weight) {
        //      System.out.println(i + " " + weight);

        if (i == 0 || weight == 0)
            return 0;
        if (w[i] <= weight)
            return max(memo(i - 1, weight),
                       memo(i - 1, weight - w[i]) + p[i]);
        return memo(i - 1, weight);
    }
}

class BottomUpSolver extends KnapasackSolver() {
    double solve() {
        int [] best = new int[weight + 1];

        for (int i = 0; i < n; i++) {
            for (int j = weight; j >= 1; j--) {
                best[j] = max(best[j], j - w[i] < 0 ? 0 : best[j - w[i]] + p[i]);
            }
        }
        return best[weight];
    }
}

class Main {

    static int w[], p[];
    static double memo[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            // read
            int n = sc.nextInt();
            int maxWeight = sc.nextInt();

            w = new int[n];
            p = new int[n];
            memo = new double[n][maxWeight + 1];
        }

        //     for (int i = 0; i < n; i++)
        //         for (int j = 0; j < maxWeight + 1; j++)
        //             memo[i][j] = -1;

        //     for (int i = 0; i < n; i++){
        //         w[i] = sc.nextInt();
        //         p[i] = sc.nextInt();
        //     }

        //     // solve and print
        //     System.out.println(profit(n - 1, maxWeight));
        //     System.out.println(profit(n - 1, maxWeight));
        //     System.out.println(profitMemo(n - 1, maxWeight));
        //     System.out.println(bottomUp(n, maxWeight));

        //     // TODO add timing code
        // }
        sc.close();

        (new CompleteSearchSolver(w, p)).solve();
        (new CompleteSearchPrunedSolver(w, p)).solve();

        (new MemoSolver(w, p)).solve();
        (new BottomUpSolver(w, p)).solve();
    }
}
