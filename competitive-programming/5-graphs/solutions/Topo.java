import java.util.*;
import java.io.*;

public class Topo {

    static LinkedList<Integer> order = new LinkedList<>();
    static List<List<Integer>> graph;
    static boolean seen[];

    private static void dfs(int start) {
	seen[start] = true;
	for (int i : graph.get(start)) {
	    if (!seen[i]) {
		dfs(i);
	    }
	}
	order.addFirst(start);
    }

    private static void topoSort(List<List<Integer>> graph) {
	order.clear();
	int n = graph.size();
	seen = new boolean[n + 1];
	for (int i = 0; i < n; i++)
	    if (!seen[i])
		dfs(i);
    }

    public static void main(String[] args) throws Exception {
	graph = GraphLib.readGraphFromDotFile("in.dot");

	System.out.println("Read graph:");
	GraphLib.prettyPrintGraph(graph);

	topoSort(graph);

	System.out.println("\nTopological ordering:");
	System.out.println("  " + order);
    }
}
