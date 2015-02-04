import java.util.*;

import static java.lang.Math.*;

abstract class KnapsackSolver {

    protected int w[], p[];
    protected double memo[][];
    protected int calls;

    abstract double solve2(int i, int weight);

    Result solve(int [] w, int [] p, int maxWeight) {
        this.w = w;
        this.p = p;
        this.calls = 0;
        double res = solve2(w.length - 1, maxWeight);
        return new Result(res, this.calls);
    }
}

class CompleteSearchSolver extends KnapsackSolver {
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

    @Override
    Result solve(int [] w, int [] p, int maxWeight) {
        this.w = w;
        this.p = p;
        memo = new double[w.length + 1][maxWeight + 1];
        for (int i = 0; i < w.length; i++)
            for (int j = 0; j < maxWeight + 1; j++)
                memo[i][j] = -1;
        this.calls = 0;
        double res = solve2(w.length - 1, maxWeight);
        return new Result(res, this.calls);
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
        calls++;
        if (i == 0 || weight == 0)
            return 0;
        if (w[i] <= weight)
            return max(memo(i - 1, weight),
                       memo(i - 1, weight - w[i]) + p[i]);
        return memo(i - 1, weight);
    }
}

class BottomUpSolver extends KnapsackSolver {

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

        TestRunner tr = new TestRunner();

        // add various solvers
        tr.addSolver(new CompleteSearchSolver());
        tr.addSolver(new CompleteSearchPrunedSolver());
        tr.addSolver(new MemoSolver());
        tr.addSolver(new BottomUpSolver());

        // run benchmarks
        tr.run();
    }
}

class TestRunner {

    List<KnapsackSolver> solvers = new ArrayList<>();

    void addSolver(KnapsackSolver k) {
        solvers.add(k);
    }

    Problem generateProblem() {
        // read problem from file
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxWeight = sc.nextInt();
        int [] w = new int[n];
        int [] p = new int[n];
        for (int i = 0; i < n; i++){
            w[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }
        sc.close();

        return new Problem(w, p, maxWeight);
    }

    void run() {
        Problem p = generateProblem();
        List<Double> results = new ArrayList<>();
        for (KnapsackSolver ks : solvers) {
            long start = System.currentTimeMillis();
            Result r = ks.solve(p.w, p.p, p.maxWeight);
            long tookMs = System.currentTimeMillis() - start;
            System.out.format("Result: %f Took (ms): %d calls %d\n",
                              r.result, tookMs, r.calls);
        }
    }
}

class Problem {
    final int [] w;
    final int [] p;
    final int maxWeight;
    Problem(int [] w, int [] p, int maxWeight) {
        this.w = w;
        this.p = p;
        this.maxWeight = maxWeight;
    }
}

class Result {
    int calls;
    double result;
    Result(double result, int calls) {
        this.calls = calls;
        this.result = result;
    }
}
