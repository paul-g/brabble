import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    static final int MAX_BASE = 36;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc.nextBigInteger(MAX_BASE),
                  sc.nextBigInteger(MAX_BASE));
        }
    }

    static void solve(BigInteger a, BigInteger b) {
        boolean equal = false;
        int b1 = 2, b2 = 2;
        String as = a.toString(MAX_BASE).toUpperCase();
        String bs = b.toString(MAX_BASE).toUpperCase();
        for (; b1 <= MAX_BASE && !equal; b1++) {
            BigInteger aInB1 = null;
            try {
                 aInB1 = new BigInteger(as, b1);
            } catch (NumberFormatException ne) {
                continue;
            }
            for (b2 = 2; b2 <= MAX_BASE && !equal; b2++) {
                try {
                    BigInteger bInB2 = new BigInteger(bs, b2);
                    equal = aInB1.equals(bInB2);
                } catch (NumberFormatException ne) {}
            }
        }

        if (equal)
            System.out.format("%s (base %d) = %s (base %d)\n",
                              as, b1 - 1, bs, b2 - 1);
        else
            System.out.format("%s is not equal to %s in any base 2..36\n",
                              as, bs);
    }
}
