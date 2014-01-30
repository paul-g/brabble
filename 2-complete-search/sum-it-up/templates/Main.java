import java.util.*;

public class Main {

    static final int MAX_N = 20;
    static int vals[] = new int[MAX_N];
    static boolean found;

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int t = sc.nextInt();
            int n = sc.nextInt();
            if (t == 0 && n == 0)
                break;

            for (int i = 0; i < n; i++)
                vals[i] = sc.nextInt();

            System.out.format("Sums of %d:\n", t);
            found = false;

	    solveRec(t, 0, 0, 0);
	    
            if (!found)
                System.out.println("NONE");
        }

        sc.close();
    }

    public static void solveRec(int total, int size, int start, int sumSoFar) {
    }

}
