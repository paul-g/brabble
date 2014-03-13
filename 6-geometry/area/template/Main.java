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
	double area = 0;

	sc.close();
        return area;
    }

    // Using arbitrary precision arithmetic.
    public static double solveBigDecimal() throws Exception {
        Scanner sc = new Scanner(new File("test.in"));
        int n = sc.nextInt();
	BigDecimal area = new BigDecimal("0");

        sc.close();
        return area.doubleValue();
    }
}
