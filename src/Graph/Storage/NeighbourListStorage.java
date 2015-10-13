package Graph.Storage;

import Graph.Edge.Edge;
import Graph.Exception.EdgeNotFound;
import Graph.Exception.VertexIdNotFound;
import Graph.Vertex.Vertex;

public class NeighbourListStorage implements Storage {
    @Override
    public Vertex[] getVertices() {
        return new Vertex[0];
    }

    @Override
    public Vertex getVertex(int id) throws VertexIdNotFound {
        return null;
    }

    @Override
    public void addVertex(Vertex vertex) {

    }

    @Override
    public void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound {

    }

    @Override
    public Edge getEdge(Vertex start, Vertex end) throws VertexIdNotFound, EdgeNotFound {
        return null;
    }

    @Override
    public void addEdge(Edge edge) throws VertexIdNotFound {

    }

    @Override
    public void removeEdge(Edge edge) throws EdgeNotFound {

    }

    @Override
    public Edge[] getEdgesIncidentTo(Vertex vertex) throws VertexIdNotFound {
        return new Edge[0];
    }

    @Override
    public int getVertexCount() {
        return 0;
    }

    @Override
    public int getEdgeCount() {
        return 0;
    }
}
