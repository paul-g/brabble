import java.util.*;
import static java.lang.Math.*;

public class Main {

    static double p, q, r, s, t, u;
    static final double EPS = 0.000001;
    static final double NO_SOL_BOUND = 0.000000001;

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
	    // read
            p = sc.nextDouble();
            q = sc.nextDouble();
            r = sc.nextDouble();
            s = sc.nextDouble();
            t = sc.nextDouble();
            u = sc.nextDouble();

            double high = 1;
            double low = 0;

	    System.out.format("%.4f\n", solve(high, low));
        }
    }

    public static double solve(double high, double low) {
	return 0.0;
    }

}
