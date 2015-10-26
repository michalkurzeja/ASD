package Graph;

public interface Storage<V> {
    Vertex findVertex(V data);
    Vertex addVertex(V data);
    void removeVertex(Vertex<V> vertex);
    Edge getEdge(Vertex<V> start, Vertex<V> end);
    Edge addEdge(Vertex<V> start, Vertex<V> end, int weight);
    void removeEdge(Edge<Vertex<V>> edge);
    Vertex<V>[] getNeighboursOf(Vertex<V> vertex);
    Edge[] getEdgesIncidentTo(Vertex<V> vertex);
    Edge[] getEdgesOutgoingFrom(Vertex<V> vertex);
    Vertex<V>[] getVertices();
    Edge[] getEdges();
    int getVertexCount();
    int getEdgeCount();
}
