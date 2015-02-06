import java.util.*;

import static java.lang.Math.*;

abstract class KnapsackSolver {

    protected Integer w[], p[];
    protected long calls;

    Result run(Integer [] w, Integer [] p, int maxWeight) {
        this.w = w;
        this.p = p;
        this.calls = 0;
        int res = solve(w.length - 1, maxWeight);
        return new Result(res, this.calls);
    }

    abstract int solve(int i, int weight);
}

class CompleteSearch extends KnapsackSolver {

    @Override
    /**
     * @param currentItem the item being considered (starts from N to 1)
     */
    int solve(int currentItem, int spareCapacity) {
        this.calls++;
        if (currentItem == 0) return 0;
        if (w[currentItem] > spareCapacity)
            return solve(currentItem - 1, 
                         spareCapacity);
        int profitIfNotPicked = solve(currentItem - 1,  spareCapacity);
        int profitIfPicked = 
           solve(currentItem - 1, 
                 spareCapacity - w[currentItem]) + 
            p[currentItem];

        return max(profitIfNotPicked, profitIfPicked);
    }
}

class CompleteSearchPruned extends KnapsackSolver {
    @Override
    int solve(int currentItem, int spareCapacity) {
        this.calls++;
        //System.out.println(currentItem + " " + spareCapacity);
        if (currentItem == 0) return 0;
        if (spareCapacity == 0) return 0;
        if (w[currentItem] > spareCapacity)
            return solve(currentItem - 1, 
                         spareCapacity);
        int profitIfNotPicked = solve(currentItem - 1,  spareCapacity);
        int profitIfPicked = 
            solve(currentItem - 1, 
                  spareCapacity - w[currentItem]) + 
            p[currentItem];

        return max(profitIfNotPicked, profitIfPicked);
    }
}

class MemoSolver extends KnapsackSolver {
    int [][] memo;

    @Override
    int solve (int currentItem, int spareCapacity) {
        // build cache
        memo = new int [w.length + 1][spareCapacity + 1];
        for (int i = 0; i < w.length + 1; i++)
            for (int j = 0 ;j < spareCapacity + 1; j++)
                memo[i][j] = -1;

        return solve2(currentItem, spareCapacity);
    }

    int memo(int currentItem, int spareCapacity) {
        if (memo[currentItem][spareCapacity] == -1)
            memo[currentItem][spareCapacity] = solve2(currentItem, spareCapacity);
        return memo[currentItem][spareCapacity];
    }

    int solve2(int currentItem, int spareCapacity) {
        this.calls++;
        if (currentItem == 0) return 0;
        if (spareCapacity == 0) return 0;
        if (w[currentItem] > spareCapacity)
            return memo(currentItem - 1, spareCapacity);

        int profitIfPicked = memo(currentItem - 1,
                                     spareCapacity - w[currentItem]) + p[currentItem];
        int profitIfNotPicked = memo(currentItem - 1, spareCapacity);

        return max(profitIfPicked, profitIfNotPicked);
    }
}

class BottomUpSolver extends KnapsackSolver {
    @Override
    int solve (int v, int weight) {
        int [] best = new int[weight + 1];

        for (int i = 0; i < v; i++) {
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
        
        if (args.length != 1) {
            System.out.println("Usage java Main <size>");
            return;
        }

        TestRunner tr = new TestRunner(Integer.parseInt(args[0]));

        // add various solvers
        tr.addSolver(new CompleteSearch());
        tr.addSolver(new CompleteSearchPruned());
        tr.addSolver(new MemoSolver());
        tr.addSolver(new BottomUpSolver());

        // run benchmarks
        tr.run();
    }
}

class TestRunner {

    List<KnapsackSolver> solvers = new ArrayList<>();

    private final int testSize;
    
    TestRunner(int testSize) {
        this.testSize = testSize;
    }

    void addSolver(KnapsackSolver k) {
        solvers.add(k);
    }

    Problem generateProblem() {
        // read problem from file
        Scanner sc = new Scanner(System.in);
        int maxWeight = sc.nextInt();
        Integer [] w = new Integer[testSize];
        Integer [] p = new Integer[testSize];
        for (int i = 0; i < testSize; i++){
            w[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }
        sc.close();
        return new Problem(w, p, maxWeight);
    }

    void run() {
        Problem p = generateProblem();
        List<Integer> results = new ArrayList<>();
        for (KnapsackSolver ks : solvers) {
            long start = System.currentTimeMillis();
            Result r = ks.run(p.w, p.p, p.maxWeight);
            long tookMs = System.currentTimeMillis() - start;
            System.out.format("%3d %22.40s %10d %10d %14d\n",
                              testSize,
                              ks.getClass().getSimpleName(),
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
    long calls;
    int result;
    Result(int result, long calls) {
        this.calls = calls;
        this.result = result;
    }
}
