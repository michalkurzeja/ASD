package FordBellman;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;
import Graph.Import.CSVImporter;
import Graph.ListStorage.ListStorage;
import Graph.MatrixStorage.MatrixStorage;
import Graph.Storage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class FordBellman {
    final static Integer INFINITY = Integer.MAX_VALUE-1;

    private Graph<Integer> graph;
    private Map<Vertex<Integer>, Integer> weights = new HashMap<>();
    private Map<Vertex<Integer>, Vertex<Integer>> predecessors = new HashMap<>();

    private FordBellman(Storage<Integer> storage) {
        graph = new Graph<Integer>(storage);
    }

    public static FordBellman createWithMatrixStorage() {
        return new FordBellman(new MatrixStorage<>());
    }

    public static FordBellman createWithListStorage() {
        return new FordBellman(new ListStorage<>());
    }

    public void findShortestPath(int sourceVal, int targetVal) throws IOException, NegativeCycle {
        CSVImporter.importGraph(graph, "src/FordBellman/graph.txt");

        calculateDistances(graph.findVertex(sourceVal));
    }

    private void calculateDistances(Vertex<Integer> source) throws NegativeCycle {
        for (Vertex<Integer> v : graph.getVertices()) {
            if (v.equals(source)) {
                weights.put(v, 0);
            } else {
                weights.put(v, INFINITY);
            }
        }

        Vertex<Integer> u, v;
        int w;

        for (int i = 1; i < graph.getVertexCount() - 1; i++) {
            for (Edge<Vertex<Integer>> e : graph.getEdges()) {
                u = e.getStart();
                v = e.getEnd();
                w = e.getWeight();

                if (weights.get(u).equals(INFINITY)) {
                    continue;
                }

                if (weights.get(u) + w < weights.get(v)) {
                    weights.put(v, weights.get(u) + w);
                    predecessors.put(v, u);
                }
            }
        }

        for (Edge<Vertex<Integer>> e : graph.getEdges()) {
            u = e.getStart();
            v = e.getEnd();

            if (weights.get(u).equals(INFINITY) && weights.get(v).equals(INFINITY)) {
                continue;
            }

            if (weights.get(u) + e.getWeight() < weights.get(v)) {
                throw new NegativeCycle();
            }
        }
    }
}
