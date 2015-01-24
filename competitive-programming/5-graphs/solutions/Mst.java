import java.util.*;
import java.io.*;

public class Mst {

    static List<Edge> convert(List<List<Integer>> nodes) {
        int pos = 0;
        Random r = new Random(0);
        List<Edge> edges = new ArrayList<>();
        for (List<Integer> ns : nodes) {
            for (int n : ns)
                edges.add(new Edge(pos, n, r.nextDouble()));
	    pos++;
        }
        return edges;
    }


    public static void main(String[] args) throws Exception {
        List<List<Edge>> graph = GraphLib.readUndirectedGraphFromDotFile("uin.dot");

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

        HashSet<Integer> nodes = new HashSet<>();
        List<Edge> mst = new ArrayList<>();

	nodes.add(0);
	for (Edge e : graph.get(0)) {
	    pq.add(e);
	}

        while (nodes.size() < graph.size()) {
            Edge minEdge = pq.poll();
	    System.out.println(minEdge);
	    System.out.println(nodes);
	    if (minEdge == null) {
		System.out.println("This should not happen!");
		break;
	    }

	    int newNode = -1;

            if ((nodes.contains(minEdge.from) &&
                 !nodes.contains(minEdge.to))) {
		newNode = minEdge.to;
            } else if (nodes.contains(minEdge.to) &&
                       !nodes.contains(minEdge.from)) {
		newNode = minEdge.from;
            }

	    if (newNode == -1)
		continue;

	    nodes.add(newNode);
	    mst.add(minEdge);
	    for (Edge e : graph.get(newNode)) {
		pq.add(e);
	    }
	    GraphLib.drawMst(graph, mst, "mst.dot", true);
        }

	//	GraphLib.drawMst(graph, mst, "mst.dot");
    }

}
