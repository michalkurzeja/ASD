package Test;

import asd.Graph.Edge.Edge;
import asd.Graph.Graph;
import asd.Graph.GraphImpl;
import asd.Graph.StorageAdapter.SimpleMatrixStorageAdapter;
import asd.Graph.Vertex.Vertex;

public class Main {
    public static void main(String[] args) throws Exception {
        Graph g = new GraphImpl(new SimpleMatrixStorageAdapter());

        for (int i = 0; i < 2; i++) {
            g.addVertex(new Vertex());
        }

        Vertex v1 = g.getVertex(0);
        Vertex v2 = g.getVertex(1);

        Edge e = new Edge(v1, v2);
        g.addEdge(e);
//        g.removeEdge(e);

        Edge[] edges = g.getIncidentEdgesTo(v1);

        for (Edge edge : edges) {
            System.out.println(edge.getId());
        }
    }
}
