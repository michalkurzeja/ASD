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

        Vertex v1 = graph.findVertex(1);
        Vertex v2 = graph.findVertex(16);

        System.out.println(String.format("Are neighbours: %b", graph.areNeighbours(v1, v2)));

        Vertex[] neighbours = graph.getNeighboursOf(v1);
        Edge[] edges = graph.getEdgesIncidentTo(v1);


    }
}
