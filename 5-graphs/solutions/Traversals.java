import java.util.*;
import java.io.*;


class Traversals {

    static int num = 0;
    static List<List<Integer>> nodes;
    static boolean seen[];
    static final LinkedList<Integer> order = new LinkedList<>();

    /** BFS keeps a queue of nodes to visit. At each iteration a node
        is removed from the queue and its neighbours added back (if not
        visited already). This happens until no more nodes are left to
        visit. **/
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

    /** To transform BFS into DFS, we only need to change the queue
        to a stack (and corresponding method calls). **/
    public static void dfsIterative(int start) {
        System.out.println();
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

    /** It's easier to write a DFS traversal recursively, since we can
        use the function call stack, instead of writing our
        own. Beware though that this may lead to stack overflow for
        really large graphs (typically large than 2K nodes). **/
    public static void dfs(int start) throws Exception {
        seen[start] = true;
        for (int neighbour : nodes.get(start)) {
            if (!seen[neighbour]) {
		// pretty print the traversal
		GraphLib.printDigraph(nodes, seen, neighbour, start);
                dfs(neighbour);
            }
        }
        System.out.println(start);
    }

    public static void init(int n) {
        seen = new boolean[n];
        for (boolean b : seen) {
            b = false;
        }
        order.clear();
    }

    public static void main(String[] args) throws Exception {

	GraphLib.clearDigraph();
        nodes = GraphLib.readGraphFromDotFile("in.dot");

        int n = nodes.size();
        System.out.println("Read graph: ");
        GraphLib.prettyPrintGraph(nodes);
        init(n);

        System.out.println("Traversing:");
        for (int i = 0; i < n; i++) {
            if (seen[i]) continue;
            dfsIterative(i);
	    GraphLib.printDigraph(nodes, seen, -1, -1);
        }
    }

}
