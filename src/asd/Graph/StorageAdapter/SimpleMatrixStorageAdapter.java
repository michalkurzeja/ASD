package asd.Graph.StorageAdapter;

import asd.Graph.Edge.Edge;
import asd.Graph.Exception.EdgeNotFound;
import asd.Graph.Exception.VertexIdNotFound;
import asd.Graph.Vertex.Vertex;
import java.util.LinkedList;

public class SimpleMatrixStorageAdapter implements StorageAdapter {
    final int ALLOCATION_STEP = 1;

    private Vertex[] vertices = new Vertex[ALLOCATION_STEP];
    private Edge[][] edges = new Edge[ALLOCATION_STEP][ALLOCATION_STEP];

    @Override
    public Vertex getVertex(int id) throws VertexIdNotFound {
        try {
            Vertex vertex = vertices[id];

            if (null == vertex) {
                throw new VertexIdNotFound(id);
            }

            return vertex;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new VertexIdNotFound(id);
        }
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (vertex.getId() > getMaxIndex()) {
            allocateMoreCells();
        }

        vertices[vertex.getId()] = vertex;
    }

    @Override
    public void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound {
        try {
            verifyThatVertexExists(vertex);

            for (Edge e : getIncidentEdgesTo(vertex)) {
                removeEdge(e);
            }

            vertices[vertex.getId()] = null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new VertexIdNotFound(vertex.getId());
        }
    }

    @Override
    public void addEdge(Edge edge) throws VertexIdNotFound {
        Vertex start = edge.getStart();
        Vertex end = edge.getEnd();

        verifyThatVertexExists(start);
        verifyThatVertexExists(end);

        if (edge.getId() > getMaxIndex()) {
            allocateMoreCells();
        }

        edges[start.getId()][end.getId()] = edge;
    }

    @Override
    public void removeEdge(Edge edge) throws EdgeNotFound {
        verifyThatEdgeExists(edge);

        edges[edge.getStart().getId()][edge.getEnd().getId()] = null;
    }

    @Override
    public Edge[] getIncidentEdgesTo(Vertex vertex) throws VertexIdNotFound {
        verifyThatVertexExists(vertex);

        LinkedList<Edge> edgesList = new LinkedList<>();

        for (Edge e : edges[vertex.getId()]) {
            if (null != e) {
                edgesList.push(e);
            }
        }

        return edgesList.toArray(new Edge[edgesList.size()]);
    }

    @Override
    public int getVertexCount() {
        return vertices.length;
    }

    @Override
    public int getEdgeCount() {
        return 0;
    }

    private int getMaxIndex() {
        return vertices.length - 1;
    }

    private void allocateMoreCells() {
        int oldSize = vertices.length;
        int newSize = oldSize + ALLOCATION_STEP;

        Vertex[] newVertices = new Vertex[newSize];
        System.arraycopy(vertices, 0, newVertices, 0, oldSize);
        vertices = newVertices;

        Edge[][] newEdges = new Edge[newSize][newSize];
        for (int index = 0; index < oldSize; index++) {
            System.arraycopy(edges[index], 0, newEdges[index], 0, oldSize);
        }
        edges = newEdges;
    }

    private void verifyThatVertexExists(Vertex vertex) throws VertexIdNotFound {
        getVertex(vertex.getId());
    }

    private void verifyThatEdgeExists(Edge edge) throws EdgeNotFound {
        if (edge.getId() > getMaxIndex() || null == edges[edge.getStart().getId()][edge.getEnd().getId()]) {
            throw new EdgeNotFound(edge);
        }
    }
}
