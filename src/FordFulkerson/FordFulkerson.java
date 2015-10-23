package FordFulkerson;

import Graph.Graph;
import Graph.Edge;
import Graph.Vertex;
import Graph.Import.CSVImporter;
import Graph.MatrixStorage.MatrixStorage;

import java.io.IOException;
import java.util.HashMap;

public class FordFulkerson {
    Graph<Integer> network = new Graph<Integer>(new MatrixStorage<>());
    Graph<Integer> residual = new Graph<Integer>(new MatrixStorage<>());
    HashMap<Vertex, HashMap<Vertex, Float>> flows;
    HashMap<Vertex, HashMap<Vertex, Float>> cf;

    public void run(int sourceVal, int targetVal) throws IOException {
        importGraphs();

        Vertex source = residual.findVertex(sourceVal);
        Vertex target = residual.findVertex(targetVal);

        flows = new HashMap<>();
        cf = new HashMap<>();

        Edge[] mainPath = residual.findPath(source, target);

        float minFlow = getMinAdditionalFlow(mainPath);

        while(minFlow > 0) {
            for (Edge e : mainPath) {
                flows.get(e.getStart()).put(e.getEnd(), getFlow(e.getStart(), e.getEnd()) + minFlow);
                flows.get(e.getEnd()).put(e.getStart(), getFlow(e.getEnd(), e.getStart()) - minFlow);
            }
        }
    }

    public float getMinAdditionalFlow(Edge[] path) {
        Float min = null;

        for (Edge e : path) {
            if (null == min) {
                min = e.getWeight() - getFlow(e.getStart(), e.getEnd());
            } else {
                float additionalFlow = e.getWeight() - getFlow(e.getStart(), e.getEnd());
                if (additionalFlow < min) {
                    min = additionalFlow;
                }
            }
        }

        return min;
    }

    private void putFlow(Vertex start, Vertex end, float flow) {

    }

    private float getFlow(Vertex start, Vertex end) {
        HashMap<Vertex, Float> map = flows.get(start);
        if (null == map) {
            return 0;
        }

        Float flow = map.get(end);

        if (null == flow) {
            return 0;
        }

        return flow;
    }


    public void importGraphs() throws IOException {
        CSVImporter.importGraph(network, "src/FordFulkerson/graph.txt");
        CSVImporter.importGraph(residual, "src/FordFulkerson/graph.txt");
    }
}
