import java.io.*;
import java.util.*;


class Point {
    double x, y;

    Point(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public String toString() {
	return String.format("(%f, %f)", x, y);
    }
}

class Main {

    public static boolean turn_left(Point p1, Point p2, Point p3) {
	return
	    (p2.x - p1.x) * (p3.y - p1.y) -
	    (p2.y - p1.y) * (p3.x - p1.x) < 0;
    }

    public static void main(String[] args) throws Exception {
	Scanner sc = new Scanner(new File("hull.in"));

	ArrayList<Point> points = new ArrayList<>();

	int n = sc.nextInt();

	boolean first = true;
	double smallestX = 0, smallestY = 0;

	for (int i = 0; i < n; i++) {
	    double x, y;
	    x = sc.nextDouble();
	    y = sc.nextDouble();

	    if (first || (x < smallestX) || (x == smallestX && y < smallestY)) {
		if (!first)
		    points.add(new Point(smallestX, smallestY));
		smallestX = x;
		smallestY = y;
		first = false;
	    } else {
		points.add(new Point(x, y));
	    }
	}
	sc.close();

	Point refPoint = new Point(smallestX, smallestY);


	// sort
	Collections.sort(points, new Comparator<Point>() {
		public int compare(Point p1, Point p2) {
		    return Double.compare((p1.y - refPoint.y) * (p2.x - refPoint.x),
					  (p2.y - refPoint.y) * (p1.x - refPoint.x));
		}
	    });


	points.add(refPoint);

	ArrayList<Point> deq = new ArrayList<>();

	deq.add(refPoint);
	deq.add(points.get(0));
	int pos = 1;

	while (pos < n) {

	    Point pNext = points.get(pos++);
	    Point p1 = deq.get(deq.size() - 1);
	    Point p2 = deq.get(deq.size() - 2);

	    while (!turn_left(p1, p2, pNext)) {
		deq.remove(deq.size() - 1);
		p1 = deq.get(deq.size() - 1);
		p2 = deq.get(deq.size() - 2);
	    }

	    deq.add(pNext);
	}

	System.out.println(deq.size() - 1);
	for (int i = 0; i < deq.size() - 1; i++) {
	    System.out.format("%.6f %.6f\n",
			      deq.get(i).x,
			      deq.get(i).y);
	}
    }
}
