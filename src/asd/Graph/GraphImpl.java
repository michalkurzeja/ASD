package asd.Graph;

import asd.Graph.Edge.Edge;
import asd.Graph.Exception.EdgeNotFound;
import asd.Graph.Exception.VertexIdNotFound;
import asd.Graph.StorageAdapter.StorageAdapter;
import asd.Graph.Vertex.Vertex;

public class GraphImpl implements Graph {
    StorageAdapter storage;

    public GraphImpl(StorageAdapter storage) {
        this.storage = storage;
    }

    @Override
    public Vertex getVertex(int id) throws VertexIdNotFound {
        return storage.getVertex(id);
    }

    @Override
    public void addVertex(Vertex vertex) {
        storage.addVertex(vertex);
    }

    @Override
    public void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound {
        storage.removeVertex(vertex);
    }

    @Override
    public Edge getEdge(Vertex start, Vertex end) throws VertexIdNotFound, EdgeNotFound {
        return storage.getEdge(start, end);
    }

    @Override
    public void addEdge(Edge edge) throws VertexIdNotFound {
        storage.addEdge(edge);
    }

    @Override
    public void removeEdge(Edge edge)  throws EdgeNotFound {
        storage.removeEdge(edge);
    }

    @Override
    public Edge[] getIncidentEdgesTo(Vertex vertex) throws VertexIdNotFound {
        return storage.getIncidentEdgesTo(vertex);
    }

    @Override
    public boolean areNeighbours(Vertex v1, Vertex v2) {
        return false; // TODO
    }

    @Override
    public int getVertexCount() {
        return storage.getVertexCount();
    }

    @Override
    public int getEdgeCount() {
        return storage.getEdgeCount();
    }
}
