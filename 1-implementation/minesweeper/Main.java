import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        for (int field = 1;;field++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if ( n == 0 && m == 0 ) break;
            sc.nextLine();

            if (field != 1)
                System.out.println();
            System.out.println("Field #" + field +":");
            solve(n, m);
        }
        sc.close();
    }

    public static void solve(int n, int m) {
        int count[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] line = sc.nextLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if ( line[j] != '*' )
                    continue;
                for (int l = i - 1; l <= i + 1  && l < n; l++)
                    for (int c = j - 1; c <= j + 1 && c < m; c++)
                        if (l >= 0 && c >= 0 && count[l][c] != -1)
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
