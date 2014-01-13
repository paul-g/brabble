import java.util.*;
import java.io.*;

import static java.lang.Math.*;

public class Main {

    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.nextInt(); // skip number of tests
        while ( sc.hasNext() ) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            boolean found = false;

            for (int x = -10000; x <= 10000 && !found; x++)
                for (int y = x + 1; y <= 10000 && !found; y++) {
                    // we get TLE without the optimisation below
                    if ( x * y > b)
                        break;
                    int z = (a - x - y);
                    // NB make sure you check x, y, z are distinct
                    if (x != z && y != z &&
                        z * y * x == b &&
                        z * z + y * y + x * x == c) {
                        System.out.format("%d %d %d\n", x, y, z);
                        found = true;
                    }
                }

            if (!found)
                System.out.println("No solution.");
        }
    }
}
