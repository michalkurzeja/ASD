package asd.Graph;

import asd.Graph.Edge.Edge;
import asd.Graph.Exception.EdgeNotFound;
import asd.Graph.Exception.VertexIdNotFound;
import asd.Graph.Vertex.Vertex;

public interface Graph {
    Vertex getVertex(int id) throws VertexIdNotFound;
    void addVertex(Vertex vertex);
    void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound;
    Edge getEdge(Vertex start, Vertex end) throws VertexIdNotFound, EdgeNotFound;
    void addEdge(Edge edge) throws VertexIdNotFound;
    void removeEdge(Edge edge)  throws EdgeNotFound;
    Edge[] getIncidentEdgesTo(Vertex vertex) throws VertexIdNotFound;
    boolean areNeighbours(Vertex v1, Vertex v2);
    int getVertexCount();
    int getEdgeCount();
}
