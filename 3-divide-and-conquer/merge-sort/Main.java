import java.util.*;

class Main {

    static int [] v = {9, 3, 8, 2, 1, 5, 1, 11};

    public static void main(String[] args) {

        mergeSort(0, v.length - 1);

	System.out.println(Arrays.toString(v));
    }

    public static void mergeSort(int low, int high) {

        int size = high - low + 1;

        if (size <= 1)
            return;

        int mid = (low + high) / 2;

        mergeSort(low, mid);
        mergeSort(mid + 1, high);

        int p1 = low, p2 = mid + 1;

        int sol[] = new int[size];
        int psol = 0;

        while (p1 <= mid && p2 <= high)
            sol[psol++] = v[p1] < v[p2] ? v[p1++] : v[p2++];

        while (p1 <= mid) sol[psol++] = v[p1++];

        while (p2 <= high) sol[psol++] = v[p2++];

	System.arraycopy(sol, 0, v, low, size);
    }
}
