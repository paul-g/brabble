import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        for (int t = 0;;t++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n == m && m == 0)
                break;

            sc.nextLine();

            if (t >= 1)
                System.out.println();

            System.out.format("Field %d\n", t);

            solve(n, m);
        }

    }

    public static void solve(int n, int m) {
        int count[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            char [] line = sc.nextLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (line[j] != '*') {
                    continue;
                }
                count[i][j] = -1;
                for (int l = i - 1; l <= i + 1; l++)
                    for (int c = j - 1; c <= j + 1; c++)
                        if (l >= 0 && c >= 0 && l < n && c < m && count[l][c] != -1)
                            count[l][c]++;
                count[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(count[i][j] == -1 ? "*" : count[i][j]);
            System.out.println();
        }

    }

}
