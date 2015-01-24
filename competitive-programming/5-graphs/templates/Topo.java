import java.util.*;
import java.io.*;

public class Topo {

    static LinkedList<Integer> order = new LinkedList<>();
    static List<List<Integer>> graph;
    static boolean seen[];

    private static void topoSort(List<List<Integer>> graph) {
	// TODO add topological sorting of graph
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
