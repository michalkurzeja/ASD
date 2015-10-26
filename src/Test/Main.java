package Test;

import Graph.Graph;
import Graph.Edge;
import Graph.ListStorage.ListStorage;
import Graph.Vertex;
import Graph.Import.CSVImporter;
import Graph.MatrixStorage.MatrixStorage;

@SuppressWarnings("unchecked")
public class Main {
    public static void main(String[] args) throws Exception {
        Graph<Integer> graph = new Graph<Integer>(new ListStorage<>());

//        CSVImporter.importGraph(graph, "src/Test/graf.txt");

        Vertex v1 = graph.addVertex(1);
        Vertex v2 = graph.addVertex(2);
        Vertex v3 = graph.addVertex(3);

        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);
        graph.addEdge(v2, v3);

        Edge[] edges = graph.getEdges();

        System.out.println(String.format("Are neighbours: %b", graph.areNeighbours(v1, v2)));

        Edge[] path = graph.findPath(v1, v3);
    }
}
