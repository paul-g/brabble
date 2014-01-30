import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {

    static long a, b, c;
    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.nextLong(); // skip number of tests
        while ( sc.hasNext() ) {
            a = sc.nextLong();
            b = sc.nextLong();
            c = sc.nextLong();
            solve2((int)Math.pow((double)b, 1.0/3.0) + 1);
        }
    }

    public static boolean check(long x, long y, long z) {
        if (x != z && y != z &&
            z * y * x == b &&
            z * z + y * y + x * x == c) {
            System.out.format("%d %d %d\n", lx, mid, mz);
            return true;
        }
        return false;
    }

    public static void solve(long bound) {
        for (long x = -bound; x <= bound; x++)
            for (long y = x + 1; y <= bound; y++) {
                if ( x * y > b)
                    break;
                long z = (a - x - y);
                if (check(x, y, (a - x - y)))
                    return;
            }

        System.out.println("No solution.");
    }

}
