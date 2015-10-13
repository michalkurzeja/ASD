package Graph.Storage;

import Graph.Edge.Edge;
import Graph.Exception.EdgeNotFound;
import Graph.Exception.VertexIdNotFound;
import Graph.Vertex.Vertex;

public interface Storage {
    Vertex[] getVertices();
    Vertex getVertex(int id) throws VertexIdNotFound;
    void addVertex(Vertex vertex);
    void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound;
    Edge getEdge(Vertex start, Vertex end) throws VertexIdNotFound, EdgeNotFound;
    void addEdge(Edge edge) throws VertexIdNotFound;
    void removeEdge(Edge edge) throws EdgeNotFound;
    Edge[] getEdgesIncidentTo(Vertex vertex) throws VertexIdNotFound;
    int getVertexCount();
    int getEdgeCount();
}
