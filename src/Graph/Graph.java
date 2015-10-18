package Graph;

public class Graph<V> {
    Storage<V> storage;

    public Graph(Storage<V> storage) {
        this.storage = storage;
    }

    public Vertex findVertex(V data) {
        return storage.findVertex(data);
    }

    public Vertex addVertex(V data) {
        return storage.addVertex(data);
    }

    public void removeVertex(Vertex vertex) {
        storage.removeVertex(vertex);
    }

    public Edge getEdge(Vertex start, Vertex end) {
        return storage.getEdge(start, end);
    }

    public void addEdge(Vertex start, Vertex end) {
        storage.addEdge(start, end, Edge.DEFAULT_WEIGHT);
    }

    public Edge addEdge(Vertex start, Vertex end, float weight) {
        return storage.addEdge(start, end, weight);
    }

    public void removeEdge(Edge edge) {
        storage.removeEdge(edge);
    }

    public Vertex[] getNeighboursOf(Vertex vertex) {
        return storage.getNeighboursOf(vertex);
    }

    public Edge[] getEdgesIncidentTo(Vertex vertex) {
        return storage.getEdgesIncidentTo(vertex);
    }

    public boolean areNeighbours(Vertex v1, Vertex v2) {
        Vertex[] neighbours = getNeighboursOf(v1);

        for (Vertex neighbour : neighbours) {
            if (neighbour.equals(v2)) {
                return true;
            }
        }

        return false;
    }

    public Vertex[] getVertices() {
        return storage.getVertices();
    }

    public int getVertexCount() {
        return storage.getVertexCount();
    }

    public int getEdgeCount() {
        return storage.getEdgeCount();
    }
}
