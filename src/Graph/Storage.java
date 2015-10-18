package Graph;

public interface Storage<V> {
    Vertex findVertex(V data);
    Vertex addVertex(V data);
    void removeVertex(Vertex<V> vertex);
    Edge getEdge(Vertex<V> start, Vertex<V> end);
    Edge addEdge(Vertex<V> start, Vertex<V> end, float weight);
    void removeEdge(Edge<Vertex<V>> edge);
    Vertex[] getNeighboursOf(Vertex<V> vertex);
    Edge[] getEdgesIncidentTo(Vertex<V> vertex);
    Vertex[] getVertices();
    int getVertexCount();
    int getEdgeCount();
}
