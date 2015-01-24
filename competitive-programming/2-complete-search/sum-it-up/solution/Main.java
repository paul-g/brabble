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
        if (sum == 0) {
            found = true;
            boolean first = true;
            for (int i = 0; i < pos; i++, first=false)
                out.print(first ? sol[i] : "+" + sol[i]);
            out.println();
        }

        if (size == values.length)
            return;

        for (int i = start; i < values.length; i++) {
	    int s = sum - values[i];
            if (s >= 0) {
                sol[pos++] = values[i];
                solveRec(size + 1, i + 1, s);
                pos--;
                int valueUsed = values[i];
                while(i + 1 < values.length && values[i + 1] == valueUsed) i++;
            }
        }
    }

}

