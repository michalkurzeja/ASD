package Test;

import Graph.Graph;
import Graph.Edge;
import Graph.Vertex;
import Graph.Import.CSVImporter;
import Graph.MatrixStorage.MatrixStorage;

public class Main {
    public static void main(String[] args) throws Exception {
        Graph<Integer> graph = new Graph<Integer>(new MatrixStorage<>());

        CSVImporter.importGraph(graph, "src/Test/graf.txt");

        Vertex v = graph.findVertex(1);

        Vertex[] neighbours = graph.getNeighboursOf(v);
        Edge[] edges = graph.getEdgesIncidentTo(v);
    }
}
