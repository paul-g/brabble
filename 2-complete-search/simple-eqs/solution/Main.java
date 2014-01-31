import java.util.*;
import java.io.*;

import static java.lang.Math.*;
import static java.lang.System.out;

public class Main {

    static int a, b, c;

    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.nextInt(); // skip number of tests
        while ( sc.hasNext() ) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            solve();
        }
    }

    public static void solve() {
        int bound = 34;
        for (int x = -bound; x <= bound; x++) {
            for (int y = x + 1; y <= bound; y++) {
                int z = a - x - y;
                if ( y != z && z != x &&
                     x * y * z == b &&
                     x * x + y * y + z * z == c) {
                    int min = min(x, min(y, z));
                    int max = max(x, max(y, z));
                    int mid = a - min - max;
                    out.printf("%d %d %d\n", min, mid, max);
                    return;
                }
            }
        }
        out.println("No solution.");
    }

}
