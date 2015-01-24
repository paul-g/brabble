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

	    if (signum(eval(high)) == signum(eval(low)))
		System.out.println("No solution");
	    else
                System.out.format("%.4f\n", solve(high, low));
        }
    }

    public static double solve(double high, double low) {
        double mid = (high + low) / 2;
        double res = eval(mid);
	
        if (abs(res) < EPS)
	    // found solution
            return mid;
        if (eval(mid) > 0 && eval(low) > 0)
	    // low and mid have same sign, go right
            return solve(high, mid);
	
	// else go left
        return solve(mid, low);
    }

    public static double eval(double x) {
        return p * pow(E, -x) +
            q*sin(x) + r*cos(x) + s*tan(x) + t * x * x + u;
    }

}
