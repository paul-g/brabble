import java.util.*;
import java.io.*;

import java.lang.instrument.Instrumentation;

class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}

class Traversals {

    static int num = 0;
    static List<List<Integer>> nodes;
    static boolean seen[];
    static boolean printDot = false;
    static final LinkedList<Integer> order = new LinkedList<>();

    static PrintWriter writer;

    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        seen[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            System.out.println(node);
            for (int neighbour : nodes.get(node)) {
                if (!seen[neighbour]) {
                    seen[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    public static void dfsIterative(int start) {
        System.out.println("Iterative dfs: ");
        Stack<Integer> queue = new Stack<>();

        queue.add(start);
        seen[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.pop();
            System.out.println(node);
            for (int neighbour : nodes.get(node)) {
                if (!seen[neighbour]) {
                    seen[neighbour] = true;
                    queue.push(neighbour);
                }
            }
        }
    }

    public static void dfs(int start) {
        seen[start] = true;
        for (int neighbour : nodes.get(start)) {
            if (!seen[neighbour]) {
                if (printDot)
                    printDigraph(neighbour, start);
                dfs(neighbour);
            }
        }
    }


    public static void topoSort(int start) {
        seen[start] = true;
        for (int neighbour : nodes.get(start)) {
            if (!seen[neighbour]) {
                if (printDot)
                    printDigraph(neighbour, start);
                dfs(neighbour);
            }
        }
	order.addFirst(start);
    }


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

    public static void init(int n) {
        seen = new boolean[n];
	order.clear();
    }

    private static void printColor(int node, String color) {
        System.out.format("n%d [fillcolor = %s]\n", node, color);
    }

    public static void printDigraph(int neighbour, int start) {
        writer.println("digraph G" + num + "{");
        num++;
        writer.println("{");
        writer.println("node [style=filled]");
        for (int i = 0; i < seen.length; i++)
            if (i == neighbour)
                printColor(i, "blue");
            else if (i == start)
                printColor(i, "green");
            else if  (seen[i])
                printColor(i, "red");

        writer.println("}");
        int p = 0;
        for (List<Integer> ns : nodes) {
            for (int n : ns)
                writer.println("n" + p + " -> n" + n);
            writer.println();
            p++;
        }
        writer.println("}");
    }

    public static void printGraph() {
        int pos = 0;
        for (List<Integer> ns : nodes) {
            System.out.format("%d --> ", pos++);
            System.out.println(ns);
        }
    }

    public static void main(String[] args) throws Exception {
        writer = new PrintWriter("graph.dot");
        int n = 10;
        //        nodes = fullyConnected(n);
        nodes = randomlyConnected(n);
        printGraph();
        init(n);
        //        System.out.println(nodes);
        //      System.out.println("digraph G { ");
        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;
            //      dfs(i);
            //      bfs(i);
            dfsIterative(i);
            if (printDot)
                printDigraph(-1, -1);
        }

	init(n);
        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;
	    topoSort(i);
	}
	System.out.println(order);

        //      System.out.println("}");
        // System.out.println("Done. Going to sleep...");
        // Integer s = new Integer(Integer.MAX_VALUE);
        // System.out.println(ObjectSizeFetcher.getObjectSize(s));
        // System.out.println(s.SIZE);

        // Thread.sleep(100000);
        writer.close();
    }

}
