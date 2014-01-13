import java.util.*;

public class Main {

    static final int MAX_N = 13;

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
            solveRec(t, 0, 0);
            //solveBit(t);
            if (!found)
                System.out.println("NONE");
        }

        sc.close();
    }

    public static void solveRec(int total, int size, int start) {
        int sum = 0;
        for (int i = 0; i < vals.length; i++) {
            if (seen[i] == 1) sum += vals[i];
        }

        // Check whether we have a valid solution, if so print it
        if (sum == total) {
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
            solveRec(total, size + 1, i + 1);
            seen[i] = 0;
            // Need to skip values
            int prev = vals[i];
            while (i + 1 < vals.length && vals[i + 1] == prev) i++;
        }
    }

    // Bit version is faster, but can't control generation order.
    // So it's not useful for this problem, but it's a nice trick to know.
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
