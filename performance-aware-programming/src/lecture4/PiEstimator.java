import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Estimates the value of PI using a Monte Carlo type algorithm.
 *  Simulates picking nSamples random points inside a unit square
 *  (1X1, lower left corner at (0, 0), upper right corner at (1, 1).
 *  It counts how many of the points are also inside the circle with
 *  center (0.5, 0.5) of radius 0.5.
 *
 *  We then use the fact the probability of picking a point pointInCircle the
 *  circle is equal to the ratio of the areas of the circle and square
 *  to approximate the value of Pi.
 */
public class PiEstimator implements  Runnable {

    private final int nSamples;
    private int inCircle = 0;
    private List<Point> points = new ArrayList<Point>(); // points sampled so far

    public PiEstimator(int nSamples) {
        this.nSamples = nSamples;
    }

    private static void multiThreadedPi(int nThreads, int nSamples) throws Exception {
        int samplesPerThread = nSamples/ nThreads;
        ExecutorService es = Executors.newFixedThreadPool(nThreads);

        PiEstimator[] estimators = new PiEstimator[nThreads];
        Future[] futures = new Future[nThreads];
        for (int t = 0; t < nThreads; t++) {
            estimators[t] = new PiEstimator(samplesPerThread);
            futures[t] = es.submit(estimators[t]);
        }

        int totalIn = 0;
        for (int t = 0; t < nThreads; t++) {
            futures[t].get();
            totalIn += estimators[t].getInCircle();
        }

        double pi = (double)totalIn/ nSamples / 0.25;
        System.out.println("PI = " + pi);
        es.shutdown();
    }

    private static void singleThreadPi(int nSamples) {
        PiEstimator pe = new PiEstimator(nSamples);
        pe.run();
        int in = pe.getInCircle();

        double pi = (double)in / nSamples / 0.25;
        System.out.println("PI = " + pi);
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < nSamples; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            boolean inside = pointInCircle(x, y);
            addPoint(x, y, inside);
            if (inside) {
                inCircle++;
            }
        }
    }

    private boolean pointInCircle(double x, double y) {
        x -= 0.5;
        y -= 0.5;
        return x * x + y * y <= 0.25;
    }

    private int getInCircle() {
        return inCircle;
    }

    public synchronized void addPoint(double x, double y, boolean inside) {
        points.add(new Point(x, y, inside));
    }

    public synchronized List<Point> getNewPoints() {
        List<Point> pointsCopy = new ArrayList<Point>(points);
        points.clear();
        return pointsCopy;
    }

    public static void main(String args[]) throws Exception {
        int nSamples = (int)1E8;
        long  start = System.currentTimeMillis();
        singleThreadPi(nSamples);
        System.out.println("Single Took: " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        int nThreads = 4;
        multiThreadedPi(nThreads, nSamples);
        System.out.println("Multi  Took: " + (System.currentTimeMillis() - start));
    }
}
