import java.util.*;
import java.io.*;

public class Mst {

    public static void main(String[] args) throws Exception {
        List<List<Edge>> graph = GraphLib.readUndirectedGraphFromDotFile("uin.dot");

	List<Edge> mst = new ArrayList<>();
	// TODO Implement Prim's MST Algorithm

	GraphLib.drawMst(graph, mst, "mst.dot", false);
    }

}
