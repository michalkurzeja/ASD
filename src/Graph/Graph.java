package Graph;

import Graph.Edge.Edge;
import Graph.Exception.EdgeNotFound;
import Graph.Exception.VertexIdNotFound;
import Graph.Storage.Storage;
import Graph.Vertex.Vertex;

public class Graph {
    Storage storage;

    public Graph(Storage storage) {
        this.storage = storage;
    }
    
    public Vertex[] getVertices() {
        return storage.getVertices();
    }
    
    public Vertex getVertex(int id) throws VertexIdNotFound {
        return storage.getVertex(id);
    }

    public void addVertex(Vertex vertex) {
        storage.addVertex(vertex);
    }
    
    public void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound {
        storage.removeVertex(vertex);
    }

    public Edge getEdge(Vertex start, Vertex end) throws VertexIdNotFound, EdgeNotFound {
        return storage.getEdge(start, end);
    }

    public void addEdge(Edge edge) throws VertexIdNotFound {
        storage.addEdge(edge);
    }

    public void removeEdge(Edge edge)  throws EdgeNotFound {
        storage.removeEdge(edge);
    }
    
    public Edge[] getEdgesIncidentTo(Vertex vertex) throws VertexIdNotFound {
        return storage.getEdgesIncidentTo(vertex);
    }

    public boolean areNeighbours(Vertex v1, Vertex v2) {
        return false; // TODO
    }

    public int getVertexCount() {
        return storage.getVertexCount();
    }
    
    public int getEdgeCount() {
        return storage.getEdgeCount();
    }
}
