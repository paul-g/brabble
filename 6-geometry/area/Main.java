import java.io.*;
import java.util.*;
import java.math.*;

/** Finds the area of a polygon using double and arbitrary precision.
    The polygon is given as its set of vertices in plane.

    The main idea is to compute the signed area of all triangles
    formed by two adjacent vertices of the polyogn and a fixed
    arbitray point in plane (in this case P = (0, 0) is a convenient
    choices).
*/
class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        double dpArea = solveDouble();
        System.out.format("%.6f\n", dpArea);
        System.out.format("Double took: %f s\n",
                          (System.currentTimeMillis() - start) / 1000.0);

	start = System.currentTimeMillis();
        double bdArea = solveBigDecimal();
        System.out.format("%.6f\n", bdArea);
        System.out.format("BigDecimal took: %f s\n",
                          (System.currentTimeMillis() - start) / 1000.0);
    }

    // using double precision arithmetic.
    public static double solveDouble() throws Exception {
        Scanner sc = new Scanner(new File("test.in"));
        int n = sc.nextInt();
        double x, y, first_x, first_y, area = 0.0;
        double prev_x = sc.nextDouble();
        double prev_y = sc.nextDouble();
        first_x = prev_x;
        first_y = prev_y;

        for(int i = 0; i < n - 1; i++) {
            x = sc.nextDouble();
            y = sc.nextDouble();
            area += prev_x * y - x * prev_y;
            prev_x = x;
            prev_y = y;
        }
        area += prev_x * first_y - prev_y * first_x;

        sc.close();
        return area/2;
    }

    // Using arbitrary precision arithmetic.
    public static double solveBigDecimal() throws Exception {
        Scanner sc = new Scanner(new File("test.in"));
        int n = sc.nextInt();
        BigDecimal x, y, first_x, first_y, area = new BigDecimal("0");
        BigDecimal prev_x = sc.nextBigDecimal();
        BigDecimal prev_y = sc.nextBigDecimal();
        first_x = prev_x;
        first_y = prev_y;

        for(int i = 0; i < n - 1; i++) {
            x = sc.nextBigDecimal();
            y = sc.nextBigDecimal();
            area = area.add(prev_x.multiply(y).subtract(x.multiply(prev_y)));
            prev_x = x;
            prev_y = y;
        }

        area = area.add(prev_x.multiply(first_y).subtract(first_x.multiply(prev_y)));

        sc.close();
        return area.divide(new BigDecimal("2")).doubleValue();
    }
}
