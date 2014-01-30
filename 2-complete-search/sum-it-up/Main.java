import java.util.*;

public class Main {

    static final int MAX_N = 20;

    static int vals[] = new int[MAX_N];
    // seen[i] = 1 iff we have included vals[i] in the current subset
    static int seen[] = new int[MAX_N];
    // whether we've found any valid solution
    static boolean found = false;

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int t = sc.nextInt();
            int n = sc.nextInt();
            if (t == 0 && n == 0)
                break;

            vals = new int[n];
            for (int i = 0; i < n; i++)
                vals[i] = sc.nextInt();

            System.out.format("Sums of %d:\n", t);
            found = false;

            if (args.length > 0 && args[0].equals("bit")) {
                solveBit(t);
            } else
                solveRec(t, 0, 0, 0);

            if (!found)
                System.out.println("NONE");
        }

        sc.close();
    }

    public static void solveRec(int total, int size, int start, int sumSoFar) {

        // stop when sum is larger than what we need
        if (sumSoFar > total)
            return;

        // Check whether we have a valid solution, if so print it
        if (sumSoFar == total) {
            found = true;
            boolean first = true;
            for (int i = 0; i < vals.length; i++) {
                if (seen[i] == 1) {
                    System.out.print(first ? vals[i] : "+" + vals[i]);
                    first = false;
                }
            }
            System.out.println();
        }

        // Stop when we used all numbers
        if (size == vals.length)
            return;


        // Generate possible candidates for the next level
        for (int i = start; i < vals.length; i++) {
            seen[i] = 1;
            solveRec(total, size + 1, i + 1, sumSoFar + vals[i]);
            seen[i] = 0;

            // Skip identical values, to avoid generating the
            // same solution multiple times
            int prev = vals[i];
            while (i + 1 < vals.length && vals[i + 1] == prev) i++;
        }

    }

    // can't really control order of generation or apply pruning
    public static void solveBit(int total) {
        for (int i = 0; i <= 1 << vals.length; i++) {
            int sum = 0;
            for (int j = 0; j < vals.length; j++) {
                int mask = 1 << j;
                if ((i & mask) == mask)
                    sum += vals[j];
            }
            if (sum == total) {
                for (int j = 0; j < vals.length; j++) {
                    int mask = 1 << j;
                    if ((i & mask) == mask)
                        System.out.print(vals[j] + "+");
                }
                System.out.println();
                found = true;
            }
        }
    }

}
