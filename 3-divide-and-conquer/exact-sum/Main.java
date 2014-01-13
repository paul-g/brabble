import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Main {

    public static void main (String [] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        boolean first = true;
        while (sc.hasNext()) {
            first = false;
            int n = sc.nextInt();
            List<Integer> l = new ArrayList<Integer>();
            for (int i = 0; i < n; i++)
                l.add(sc.nextInt());
            int t = sc.nextInt();
            Collections.sort(l);
            int smallestDiff = Integer.MAX_VALUE;
            int besta = 0, bestb = 0;
            for (int i = 0; i < n; i++) {
                int a = l.get(i);
                int pos = Collections.binarySearch(l, t - a);
                if (pos < 0 || pos == i)
                    continue;
                int b = l.get(pos);
                if (abs(a - b) < smallestDiff) {
                    besta = min(a, b);
                    bestb = max(a, b);
                    smallestDiff = abs(besta - bestb);
                }
            }
            System.out.format("Peter should buy books whose prices are %d and %d.\n\n",
                              besta, bestb);
        }

    }

}
