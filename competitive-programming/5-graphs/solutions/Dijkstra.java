import java.util.*;

/** Pair is need to store vertex and distance from source in a
    priority queue. **/
class Pair implements Comparable<Pair> {

    int vertex;
    double distanceFromSource;

    Pair(int vertex, double distanceFromSource) {
	this.vertex = vertex;
	this.distanceFromSource = distanceFromSource;
    }

    @Override
    public int compareTo(Pair other) {
	return Double.compare(distanceFromSource, other.distanceFromSource);
    }

}

class Dijkstra {

    public static void main(String[] args) throws Exception {

	List<List<Edge>> graph = GraphLib.readDirectedGraphFromDotFile("din.dot");

	PriorityQueue<Pair> pq = new PriorityQueue<Pair>();

	int source = 0;

	int n = graph.size();
	// holds distance from source to all other nodes
	double d[] = new double[n];

	// keep track of nodes we've visited
	boolean seen[] = new boolean[n];
	for (int i = 0; i < n; i++) {
	    d[i] = Integer.MAX_VALUE;
	    seen[i] = false;
	}

	pq.add(new Pair(source, 0));
	d[source] = 0;

	while (!pq.isEmpty()) {

	    int node = pq.poll().vertex;

	    if (seen[node]) continue;

	    seen[node] = true;
	    for (Edge e : graph.get(node)) {
		double alt = d[node] + e.weight;
		int to = e.to;
		if (!seen[to] && d[to] > alt) {
		    d[to] = alt;
		    pq.add(new Pair(to, d[to]));
		}
	    }
	}

	for (double val : d)  {
	    System.out.format("%.2f ", val);
	}

	System.out.println();

    }
}
