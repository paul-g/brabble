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

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int fst = ll.get(0);
            int snd = ll.get(1);

            if (ll.size() == 2) {
                sb.append(fst + " " + snd + "\n");
                sum += snd;
                break;
            }

            int lst = ll.pollLast();

            if (ll.size() == 2) {
                sb.append(fst + " " + lst + "\n");
                sb.append(fst + "\n");
                sb.append(fst + " " + snd + "\n");
                sum += fst + snd + lst;
                break;
            }

            // general case, ll.size() >= 4
            int secondLargest = ll.pollLast();

            // (fst, lst), (fst), (fst, secondLargest), (fst)
            int firstOption = 2 * fst + lst + secondLargest;
            // (fst, snd), (fst), (secondLargset, largest), (snd)
            int secondOption = fst + 2 * snd + lst;

            if (secondOption < firstOption) {
                sb.append(String.format("%d %d\n%d\n%d %d\n%d\n",
                                        fst, snd,
                                        fst,
                                        secondLargest, lst,
                                        snd)
                          );
                sum += secondOption;
                continue;
            }

            sb.append(String.format("%d %d\n%d\n%d %d\n%d\n",
                                    fst, lst,
                                    fst,
                                    fst, secondLargest,
                                    fst)
                      );
            sum += firstOption;
        }

        System.out.println(sum);
        System.out.print(sb);
    }
}
