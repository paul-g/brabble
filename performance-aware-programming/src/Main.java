import java.util.*;

import static java.lang.Math.*;

abstract class KnapsackSolver {

    protected Integer w[], p[];
    protected int calls;

    Result run(Integer [] w, Integer [] p, int maxWeight) {
        this.w = w;
        this.p = p;
        this.calls = 0;
        double res = solve(w.length - 1, maxWeight);
        return new Result(res, this.calls);
    }

    abstract double solve(int i, int weight);
}

class CompleteSearchSolver extends KnapsackSolver {
    @Override
    double solve(int currentItem, int spareCapacity) {
        this.calls++;
        if (currentItem == 0) return 0;
        if (w[currentItem] > spareCapacity)
            return solve(currentItem - 1, spareCapacity);

        double profitIfPicked = solve(currentItem - 1,
                                      spareCapacity - w[currentItem]) + p[currentItem];
        double profitIfNotPicked = solve(currentItem - 1, spareCapacity);


        return max(profitIfPicked, profitIfNotPicked);
    }
}

class CompleteSearchPrunedSolver extends KnapsackSolver {
    @Override
    double solve(int currentItem, int spareCapacity) {
        //        System.out.println(currentItem + " " + spareCapacity);
        this.calls++;
        if (currentItem == 0) return 0;
        if (spareCapacity == 0) return 0;
        if (w[currentItem] > spareCapacity)
            return solve(currentItem - 1, spareCapacity);

        double profitIfPicked = solve(currentItem - 1,
                                      spareCapacity - w[currentItem]) + p[currentItem];
        double profitIfNotPicked = solve(currentItem - 1, spareCapacity);


        return max(profitIfPicked, profitIfNotPicked);
    }
}

class MemoSolver extends KnapsackSolver {
    double [][] memo;

    @Override
    double solve (int currentItem, int spareCapacity) {
        // build cache
        memo = new double [w.length + 1][spareCapacity + 1];
        for (int i = 0; i < w.length + 1; i++)
            for (int j = 0 ;j < spareCapacity + 1; j++)
                memo[i][j] = -1;

        return solve2(currentItem, spareCapacity);
    }

    double memo(int currentItem, int spareCapacity) {
        if (memo[currentItem][spareCapacity] == -1)
            memo[currentItem][spareCapacity] = solve2(currentItem, spareCapacity);
        return memo[currentItem][spareCapacity];
    }

    double solve2(int currentItem, int spareCapacity) {
        this.calls++;
        if (currentItem == 0) return 0;
        if (spareCapacity == 0) return 0;
        if (w[currentItem] > spareCapacity)
            return memo(currentItem - 1, spareCapacity);

        double profitIfPicked = memo(currentItem - 1,
                                     spareCapacity - w[currentItem]) + p[currentItem];
        double profitIfNotPicked = memo(currentItem - 1, spareCapacity);

        return max(profitIfPicked, profitIfNotPicked);
    }
}

class BottomUpSolver extends KnapsackSolver {
    @Override
    double solve (int v, int weight) {
        n++;
        int [] best = new int[weight + 1];

        for (int i = 0; i < n; i++) {
            for (int j = weight; j >= 1; j--) {
                this.calls++;
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
        // tr.addSolver(new BottomUpSolver());

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
        Integer [] w = new Integer[n];
        Integer [] p = new Integer[n];
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
            Result r = ks.run(p.w, p.p, p.maxWeight);
            long tookMs = System.currentTimeMillis() - start;
            System.out.format("Result: %f Took (ms): %d Calls: %d\n",
                              r.result, tookMs, r.calls);
        }
    }
}

class Problem {
    final Integer [] w;
    final Integer [] p;
    final int maxWeight;
    Problem(Integer [] w, Integer [] p, int maxWeight) {
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
