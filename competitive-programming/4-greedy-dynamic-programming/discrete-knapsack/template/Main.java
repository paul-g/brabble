import java.util.*;

import static java.lang.Math.*;

class Main {

    static int w[], p[];
    static double memo[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            // read
            int n = sc.nextInt();
            int maxWeight = sc.nextInt();

            w = new int[n];
            p = new int[n];

            for (int i = 0; i < n; i++){
                w[i] = sc.nextInt();
                p[i] = sc.nextInt();
            }

            // solve and print
            System.out.println(profit(n - 1, maxWeight));
	    //            System.out.println(profitMemo(n - 1, maxWeight));
	    //            System.out.println(bottomUp(n, maxWeight));
        }
    }

    public static double profit (int i, int weight) {
	return 0;
    }

    public static double memo(int i, int weight) {
	return 0;
    }

    public static double profitMemo (int i, int weight) {
	return 0;
    }

    public static double bottomUp(int n, int weight) {
	return 0;
    }

}
