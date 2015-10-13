package Graph.Test;

import Collection.ArrayCollection.ArrayCollection;
import Collection.HashMap.HashMap;
import Graph.Edge.Edge;
import Graph.Graph;
import Graph.Storage.MatrixStorage;
import Graph.Vertex.Vertex;

public class Main {
    public static void main(String[] args) throws Exception {
        Graph g = new Graph(new MatrixStorage(HashMap.class));

        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);

        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v1, v3);
        Edge e3 = new Edge(v2, v3);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        g.removeVertex(v1);

        System.out.println(g.getVertexCount());
        System.out.println(g.getEdgeCount());

        for (Vertex v :g.getVertices()) {
            System.out.println(v.getId());
        }
    }
}
