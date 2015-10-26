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
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("unchecked")
public class FordBellman {
    final static Integer INFINITY = Integer.MAX_VALUE;

    private Graph<Integer> graph;
    private Map<Vertex<Integer>, Integer> weights;
    private Map<Vertex<Integer>, Vertex<Integer>> predecessors;

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

        Vertex source = graph.findVertex(sourceVal);
        Vertex target = graph.findVertex(targetVal);

        long startTime = System.currentTimeMillis();
        calculateDistances(source);
        long timeElapsed = System.currentTimeMillis() - startTime;

        Vertex current = target;
        Vertex predecessor = predecessors.get(current);
        LinkedList<String> path = new LinkedList<>();
        path.push(target.getData().toString());

        while (null != predecessor) {
            path.push(graph.getEdge(predecessor, current).getStart().getData().toString());
            current = predecessor;
            predecessor = predecessors.get(current);
        }

        String[] ids = path.toArray(new String[path.size()]);

        System.out.println(String.format("Path cost: %d", weights.get(target)));
        System.out.println(String.join(" -> ", ids));
        System.out.println(String.format("Time elapsed: %d", timeElapsed));
    }

    private void calculateDistances(Vertex<Integer> source) throws NegativeCycle {
        weights = new HashMap<>(graph.getVertexCount());
        predecessors = new HashMap<>(graph.getVertexCount());

        for (Vertex<Integer> v : graph.getVertices()) {
            if (v.equals(source)) {
                weights.put(v, 0);
            } else {
                weights.put(v, INFINITY);
            }
        }

        Edge[] allEdges = graph.getEdges();
        Vertex<Integer> u, v;
        int w;

        for (int i = 1; i < graph.getVertexCount() - 1; i++) {
            for (Edge<Vertex<Integer>> e : allEdges) {
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

        for (Edge<Vertex<Integer>> e : allEdges) {
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
