import java.util.Random;

public class Threads {

    public static void main (String args[]) throws InterruptedException {
        // simpleSumExample();
        sumExample();
    }

    static void simpleSumExample () throws InterruptedException {
        int[] a = {10, 11};
        int[] b = {12, 13};

        System.out.println(
                simpleSum(a, b) + " " +
                threadedSum(a, b));
    }

    private static void sumExample() throws InterruptedException {
        for (int i = 1; i <= 1E7; i*=10) {
            int[] a = new int[i];
            int[] b = new int[i];

            long start = System.currentTimeMillis();
            simpleSum(a, b);
            System.out.print("Single thr: " + (System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            threadedSum(a, b);
            System.out.println("  Multi thr: " + (System.currentTimeMillis() - start));
        }
    }

    private static int threadedSum(int[] a, int[] b) throws InterruptedException {
        SumRunnable r1 = new SumRunnable(a);
        Thread t1 = new Thread(r1);
        SumRunnable r2 = new SumRunnable(b);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        return r1.getSum() + r2.getSum();
    }

    private static int simpleSum(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++)
            sum += a[i] + b[i];
        return sum;
    }

    private static class SumRunnable implements Runnable {
        private final int[] a;
        private int sum = 0;

        SumRunnable(int a[]) {
            this.a = a;
        }
        @Override
        public void run() {
            for (int v : a)
                sum += v;
        }

        public int getSum() {
            return sum;
        }
    }
}
