import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

    public static void main (String [] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // read
            int n = sc.nextInt();
            List<Integer> l = new ArrayList<Integer>();
            for (int i = 0; i < n; i++)
                l.add(sc.nextInt());
            int t = sc.nextInt();

            // solve
            Pair sol = solve(l, t);

            // print
            System.out.format("Peter should buy books whose prices are %d and %d.\n\n",
                              sol.x, sol.y);
        }

    }

    public static Pair solve(List<Integer> l, int t) {
        Collections.sort(l);

        int smallestDiff = Integer.MAX_VALUE;
        int besta = 0, bestb = 0;

        for (int i = 0; i < l.size(); i++) {
            int a = l.get(i);
            int pos = Collections.binarySearch(l, t - a);

            // either not found, or found the element we are currently
            // at, both are no good
            if (pos < 0 || pos == i)
                continue;

            int b = l.get(pos);
            if (abs(a - b) < smallestDiff) {
                besta = min(a, b);
                bestb = max(a, b);
                smallestDiff = abs(besta - bestb);
            }
        }

        return new Pair(besta, bestb);
    }

}

class Pair {
    int x, y;
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
