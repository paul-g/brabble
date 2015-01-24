import java.util.*;

public class Main {

    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int nt = sc.nextInt();
        for (int t = 1; t <= nt; t++) {
            solve();
            if (t < nt)
                System.out.println();
        }
        sc.close();
    }

    static void solve() {
        int n = sc.nextInt();
        double r[] = new double[n];

        for (int i = 0; i < n; i++) {
            double t = sc.nextDouble();
            double v = sc.nextDouble();
            r[i] = v / t;
        }

        for (int i = 0; i < n; i++) {
            int bestj = -1;
            for (int j = 0; j < n; j++)
                if (r[j] != -1 && (bestj == - 1 || r[j] > r[bestj]))
                    bestj = j;
            System.out.print(bestj + 1 + (i == n - 1 ? "\n" : " "));
            r[bestj] = -1;
        }
    }

}
