import java.io.*;
import java.util.*;

public class Main {

    static final Scanner sc = new Scanner(System.in);
    public static void main(String [] args) {
        int nt = sc.nextInt();
        for (int t = 1; t <= nt; t++) {
            System.out.format("Case %d: ", t);
            solve();
        }
        sc.close();
    }

    static void solve() {
        int n = sc.nextInt();
        String line = sc.next();
        int covered = 0;
        int scarecrow = 0;
        for (char c : line.toCharArray()) {
            if (c == '.' && covered == 0) {
                scarecrow++;
                covered = 3;
            }
            if (covered > 0)
                covered--;
        }
        System.out.println(scarecrow);
    }

}
