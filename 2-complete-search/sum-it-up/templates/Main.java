import java.util.*;

import static java.lang.System.out;

public class Main {

    static int values[], sol[];
    static int pos;
    static boolean found;

    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int t = sc.nextInt();
            int n = sc.nextInt();
            if (t == 0 && n == 0)
                break;

            values = new int[n];
            sol = new int[n];
	    pos = 0;

            for (int i = 0; i < n; i++)
                values[i] = sc.nextInt();

            out.format("Sums of %d:\n", t);
            found = false;

            solveRec(0, 0, t);

            if (!found)
                out.println("NONE");
        }

        sc.close();
    }

    public static void solveRec(int size, int start, int sum) {
    }

}

