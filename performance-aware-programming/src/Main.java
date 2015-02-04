import java.util.*;

import static java.lang.Math.*;

abstract class KnapsackSolver {

    protected int w[], p[];
    protected double memo[][];
    protected int cacheHits, unique, calls;

    KnapsackSolver(int[] w, int[] p) {
        this.w = w;
        this.p = p;
    }

    abstract double solve2(int i, int weight);

    double solve(int maxWeight) {
        this.calls = 0;
        this.cacheHits = 0;
        this.unique = 0;
        long start = System.currentTimeMillis();
        double res = solve2(w.length - 1, maxWeight);
        long tookMs = System.currentTimeMillis() - start;
        System.out.println(tookMs);
        // System.out.println(calls);
        // System.out.println(cacheHits);
        // System.out.println(unique);
        return res;
    }
}

class CompleteSearchSolver extends KnapsackSolver {
    CompleteSearchSolver(int[] w, int[] p) {
        super(w, p);
    }

    @Override
    double solve2(int i, int weight) {
        // performance
        // System.out.println(i + " " + weight);
        this.calls++;

        if (i == 0)
            return 0;
        if (w[i] <= weight)
            return max(solve2(i - 1, weight),
                       solve2(i - 1, weight - w[i]) + p[i]);
        return solve2(i - 1, weight);
    }
}

class CompleteSearchPrunedSolver extends KnapsackSolver {

    CompleteSearchPrunedSolver(int[] w, int[] p) {
        super(w, p);
    }

    @Override
    double solve2(int i, int weight) {
        // performance
        //        System.out.println(i + " " + weight);
        this.calls++;

        if (i == 0 || weight == 0)
            return 0;
        if (w[i] <= weight)
            return max(solve2(i - 1, weight),
                       solve2(i - 1, weight - w[i]) + p[i]);
        return solve2(i - 1, weight);
    }
}

class MemoSolver extends KnapsackSolver {

    private double memo[][];

    public MemoSolver(int w[], int p[], int maxWeight) {
        super(w, p);
        memo = new double[w.length + 1][maxWeight + 1];
        for (int i = 0; i < w.length; i++)
            for (int j = 0; j < maxWeight + 1; j++)
                memo[i][j] = -1;
    }

    public double memo(int i, int weight) {
        if (memo[i][weight] == -1)
            memo[i][weight] = solve2(i, weight);
        return memo[i][weight];
    }

    // the recurrence is the same, but we cache the results in memo to
    // avoid unnecessary computation
    @Override
    double solve2 (int i, int weight) {
        //      System.out.println(i + " " + weight);

        if (i == 0 || weight == 0)
            return 0;
        if (w[i] <= weight)
            return max(memo(i - 1, weight),
                       memo(i - 1, weight - w[i]) + p[i]);
        return memo(i - 1, weight);
    }
}

class BottomUpSolver extends KnapsackSolver {

    BottomUpSolver(int[] w, int[] p) {
        super(w, p);
    }

    @Override
    double solve2(int n, int weight) {
        n++;
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

    public static void main(String[] args) {

        // read
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxWeight = sc.nextInt();
        w = new int[n];
        p = new int[n];
        for (int i = 0; i < n; i++){
            w[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }
        sc.close();

        double result = (new CompleteSearchSolver(w, p)).solve(maxWeight);
        System.out.println(result);
        double result2 = (new CompleteSearchPrunedSolver(w, p)).solve(maxWeight);
        System.out.println(result2);
        double result3 = (new MemoSolver(w, p, maxWeight)).solve(maxWeight);
        System.out.println(result3);
        (new BottomUpSolver(w, p)).solve(maxWeight);
    }
}
