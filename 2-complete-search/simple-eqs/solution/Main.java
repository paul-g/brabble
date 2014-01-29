import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {

    static int a, b, c;
    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.nextInt(); // skip number of tests
        while ( sc.hasNext() ) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            solve(30);
        }
    }

    public static void solve(int bound) {
        for (int x = -bound; x <= bound; x++)
            for (int y = x + 1; y <= bound; y++)
                if (check(x, y, (a - x - y)))
                    return;
        System.out.println("No solution.");
    }

    public static boolean check(int x, int y, int z) {
        if (x != z && y != z &&
            z * y * x == b &&
            z * z + y * y + x * x == c) {
            int min = min(x, min(y, z));
            int max = max(x, max(y, z));
            int mid = a - min - max;
            System.out.format("%d %d %d\n", min, mid, max);
            return true;
        }
        return false;
    }
}
