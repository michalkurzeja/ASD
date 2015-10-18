package Graph;

public interface Storage<V> {
    Vertex findVertex(V data);
    Vertex addVertex(V data);
    void removeVertex(Vertex vertex);
    Edge getEdge(Vertex start, Vertex end);
    Edge addEdge(Vertex start, Vertex end, float weight);
    void removeEdge(Edge edge);
    Vertex[] getNeighboursOf(Vertex vertex);
    Edge[] getEdgesIncidentTo(Vertex vertex);
    Vertex[] getVertices();
    int getVertexCount();
    int getEdgeCount();
}
