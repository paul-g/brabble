import java.util.*;

class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int nt = sc.nextInt();
        for (int i = 0; i < nt; i++) {
            solve();
            if (i < nt - 1)
                System.out.println();
        }
    }

    static void solve() {
        int n = sc.nextInt();
        int [] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }

        if (n == 1) {
            System.out.println(vals[0] + "\n" + vals[0]);
            return;
        }

        Arrays.sort(vals);

        LinkedList<Integer> ll = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            ll.add(vals[i]);
        }

        // The general case
        int sum = 0;

        StringBuilder sb = new StringBuilder();
        while (true) {
            if (ll.size() >= 4) {
                // general case
                int smallest = ll.peekFirst();
                int secondSmallest = ll.get(1);
                int largest = ll.pollLast();
                int secondLargest = ll.peekLast();
                ll.add(largest);

                int firstOption = 2 * smallest + largest + secondLargest;
                int secondOption = smallest + 2 * secondSmallest + largest;

                if (secondOption < firstOption) {
                    sb.append(String.format("%d %d\n%d\n%d %d\n%d\n",
                                            smallest, secondSmallest,
                                            smallest,
                                            secondLargest, largest,
                                            secondSmallest)
                              );
                    sum += secondOption;
                } else {
                    sb.append(String.format("%d %d\n%d\n%d %d\n%d\n",
                                            smallest, largest,
                                            smallest,
                                            smallest, secondLargest,
                                            smallest)
                              );
                    sum += firstOption;
                }
                ll.pollLast();
                ll.pollLast();
            } else if (ll.size() == 3) {
                sum += ll.get(0) + ll.get(1) + ll.get(2);
                sb.append(ll.peekFirst() + " " + ll.pollLast() + "\n");
                sb.append(ll.peekFirst() + "\n");
                sb.append(ll.peekFirst() + " " + ll.peekLast() + "\n");
                break;
            } else if (ll.size() == 2) {
                sb.append(ll.peekFirst() + " " + ll.peekLast() + "\n");
                sum += ll.peekLast();
                break;
            }
        }

        System.out.println(sum);
        System.out.print(sb);
    }
}
