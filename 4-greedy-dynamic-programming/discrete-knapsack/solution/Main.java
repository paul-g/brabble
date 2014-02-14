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
	    memo = new double[n][maxWeight + 1];

	    for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
		    memo[i][j] = -1;
	    
            for (int i = 0; i < n; i++){
		w[i] = sc.nextInt();
		p[i] = sc.nextInt();
	    }
            
	    // solve and print
	    System.out.println(profit(n - 1, maxWeight));
	    System.out.println(profitMemo(n - 1, maxWeight));
        }
    }

	
    // the naive complete search approach
    public static double profit (int i, int weight) {
	//	System.out.println(i + " " + weight);

	if (i == 0 || weight == 0)
	    return 0;
	if (w[i] <= weight)
	    return max(profit(i - 1, weight),
		       profit(i - 1, weight - w[i]) + p[i]);
	return profit(i - 1, weight);
    }

    public static double memo(int i, int weight) {
	if (memo[i][weight] == -1)
	    memo[i][weight] = profitMemo(i, weight);
	return memo[i][weight];
    }

    // the recurrence is the same, but we cache the results in memo to
    // avoid unnecessary computation
    public static double profitMemo (int i, int weight) {
	//	System.out.println(i + " " + weight);

	if (i == 0 || weight == 0)
	    return 0;
	if (w[i] <= weight)
	    return max(memo(i - 1, weight),
		       memo(i - 1, weight - w[i]) + p[i]);
	return memo(i - 1, weight);
    }

}
