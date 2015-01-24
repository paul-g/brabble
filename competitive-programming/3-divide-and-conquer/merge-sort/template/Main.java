import java.util.*;

class Main {

    static int v[];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
	    // read
            int n = sc.nextInt();
            v = new int[n];
            for (int i = 0; i < n; i++)
                v[i] = sc.nextInt();

	    // solve
            mergeSort(0, v.length - 1);

	    // print
            System.out.println(Arrays.toString(v));
        }
    }

    public static void mergeSort(int low, int high) {
	// Solve MEEEE!
    }
}
