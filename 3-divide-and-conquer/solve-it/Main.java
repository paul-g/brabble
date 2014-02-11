import java.util.*;
import static java.lang.Math.*;

public class Main {

    static double p, q, r, s, t, u;

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            p = sc.nextDouble();
            q = sc.nextDouble();
            r = sc.nextDouble();
            s = sc.nextDouble();
            t = sc.nextDouble();
            u = sc.nextDouble();

            double high = 1;
            double low = 0;

            try {
                System.out.format("%.4f\n", solve(high, low));
            } catch (Exception e) {
                System.out.println("No solution");
            }
        }
    }

    public static double solve(double high, double low) {
        if (low + 0.000000001 > high)
            throw new RuntimeException();
        double mid = (high + low) / 2;
        double res = eval(mid);
        if (abs(res) < 0.000001)
            return mid;
        if (eval(mid) > 0 && eval(low) > 0)
            return solve(high, mid);
        return solve(mid, low);
    }

    public static double eval(double x) {
        return p * pow(E, -x) +
            q*sin(x) + r*cos(x) + s*tan(x) + t * x * x + u;
    }

}
