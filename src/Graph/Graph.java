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

    public void removeVertex(Vertex<V> vertex) {
        storage.removeVertex(vertex);
    }

    public Edge getEdge(Vertex<V> start, Vertex<V> end) {
        return storage.getEdge(start, end);
    }

    public void addEdge(Vertex<V> start, Vertex<V> end) {
        storage.addEdge(start, end, Edge.DEFAULT_WEIGHT);
    }

    public Edge addEdge(Vertex<V> start, Vertex<V> end, float weight) {
        return storage.addEdge(start, end, weight);
    }

    public void removeEdge(Edge<Vertex<V>> edge) {
        storage.removeEdge(edge);
    }

    public Vertex[] getNeighboursOf(Vertex<V> vertex) {
        return storage.getNeighboursOf(vertex);
    }

    public Edge[] getEdgesIncidentTo(Vertex<V> vertex) {
        return storage.getEdgesIncidentTo(vertex);
    }

    public boolean areNeighbours(Vertex<V> v1, Vertex<V> v2) {
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
