import java.util.*;
import java.io.*;

public class GraphLib {

    static int num = 0;

    /** Make sure list has at least size elements. **/
    private static void ensureSize(List<List<Integer>> list, int size) {
        while (list.size() <= size) {
            list.add(new ArrayList<Integer>());
        }
    }

    /** Read a graph from a dot file and return its adjacency list. **/
    public static List<List<Integer>> readGraphFromDotFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        br.readLine();
        ArrayList<List<Integer>> graph = new ArrayList<>();

	int max = 0;

        String line;
        while ( (line = br.readLine()) != null && !"}".equals(line)) {
            String ls[] = line.split("->");
            Integer from = Integer.parseInt(ls[0].trim());
            Integer to = Integer.parseInt(ls[1].trim());

	    max = Math.max(max, from);
	    max = Math.max(max, to);

            ensureSize(graph, from);

            List<Integer> neighbours = graph.get(from);
            if (neighbours == null)
                neighbours = new ArrayList<Integer>();

            neighbours.add(to);
        }

	ensureSize(graph, max);

        br.close();
        return graph;
    }

    /** Pretty print a graph given as an adjacency list. **/
    public static void prettyPrintGraph(List<List<Integer>> nodes) {
        int pos = 0;
        for (List<Integer> ns : nodes) {
            System.out.format("  %d --> ", pos++);
            System.out.println(ns);
        }
    }

    /** Returns a fully connected graph in adjacency list format. **/
    public static List<List<Integer>> fullyConnected(int n) {
        List<List<Integer>> nodes = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> ns = new LinkedList<Integer>();
            nodes.add(ns);
            for (int j = 0; j < n; j++)
                if (i != j)
                    ns.add(j);
        }
        return nodes;
    }


    /** Writes graph.dot containing a dot representation of the given graph. **/
    public static void printDigraph(List<List<Integer>> graph,
				    boolean seen[],
				    int neighbour,
				    int start) throws Exception {
	PrintWriter writer = new PrintWriter(new FileOutputStream("graph.dot", true));
        writer.println("digraph G" + num + "{");
        num++;
        writer.println("{");
        writer.println("node [style=filled]");
        for (int i = 0; i < seen.length; i++)
            if (i == neighbour)
                printColor(writer, i, "blue");
            else if (i == start)
                printColor(writer, i, "green");
            else if  (seen[i])
                printColor(writer, i, "red");

        writer.println("}");
        int p = 0;
        for (List<Integer> ns : graph) {
            for (int n : ns)
                writer.println("n" + p + " -> n" + n);
            writer.println();
            p++;
        }
        writer.println("}");
	writer.close();
    }

    private static void printColor(PrintWriter writer, int node, String color) {
        writer.print(String.format("n%d [fillcolor = %s]\n", node, color));
    }

    public static void clearDigraph() throws Exception {
	PrintWriter writer = new PrintWriter("graph.dot");
	writer.close();
    }

    /** Returns a randomly connected graph in adjacency list format. **/
    public static List<List<Integer>> randomlyConnected(int n) {
        List<List<Integer>> nodes = new LinkedList<>();
        Random r = new Random(0);
        int count = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> ns = new LinkedList<Integer>();
            nodes.add(ns);
            for (int j = 0; j < n; j++)
                if (i != j && r.nextDouble() < 0.4)
                    if (j > i || !nodes.get(j).contains(i)) {
                        ns.add(j);
                        count++;
                    }
        }
        System.out.println("Edget count: " + count);
        return nodes;
    }


}
