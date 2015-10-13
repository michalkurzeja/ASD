package Graph.Storage;

import Collection.Collection;
import Collection.Exception.KeyNotFound;
import Collection.Exception.NullKey;
import Graph.Edge.Edge;
import Graph.Exception.EdgeNotFound;
import Graph.Exception.VertexIdNotFound;
import Graph.Vertex.Vertex;

public class MatrixStorage implements Storage {
    private Collection<Integer, Vertex> vertices;
    private Collection<Integer, Collection<Integer, Edge>> edges;

    private int vertexCount = 0;
    private int edgeCount = 0;

    private Class<? extends Collection> collectionClass;

    @SuppressWarnings("unchecked")
    public MatrixStorage(Class<? extends Collection> collectionClass) {
        try {
            vertices = collectionClass.newInstance();
            edges = collectionClass.newInstance();
            this.collectionClass = collectionClass;
        } catch (InstantiationException | IllegalAccessException exception) {
            handleCollectionInstantationException(exception);
        }
    }

    @Override
    public Vertex[] getVertices() {
        Vertex[] list = new Vertex[vertices.count()];
        int index = 0;

        for (Vertex vertex : vertices.getValues(Vertex.class)) {
            list[index++] = vertex;
        }

        return list;
    }

    @Override
    public Vertex getVertex(int id) throws VertexIdNotFound {
        try {
            return vertices.get(id);
        } catch (KeyNotFound | NullKey e) {
            throw new VertexIdNotFound(id);
        }
    }

    @Override
    public void addVertex(Vertex vertex) {
        try {
            vertices.set(vertex.getId(), vertex);
            vertexCount++;
        } catch (NullKey e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeVertex(Vertex vertex) throws VertexIdNotFound, EdgeNotFound {
        try {
            for (Edge edge : getEdgesIncidentTo(vertex)) {
                removeEdge(edge);
            }

            vertices.remove(vertex.getId());
            vertexCount--;
        } catch (KeyNotFound e) {
            throw new VertexIdNotFound(vertex.getId());
        } catch (NullKey e) {
            e.printStackTrace();
        }
    }

    @Override
    public Edge getEdge(Vertex start, Vertex end) throws VertexIdNotFound, EdgeNotFound {
        try {
            return edges.get(start.getId()).get(end.getId());
        } catch (KeyNotFound | NullKey e) {
            throw new EdgeNotFound();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addEdge(Edge edge) throws VertexIdNotFound {
        Vertex start = edge.getStart();
        Vertex end = edge.getEnd();

        try {
            if (!vertices.contains(start.getId())) {
                throw new VertexIdNotFound(start.getId());
            }

            if (!vertices.contains(end.getId())) {
                throw new VertexIdNotFound(start.getId());
            }

            Collection<Integer, Edge> col;

            try {
                col = edges.get(start.getId());
            } catch (KeyNotFound e) {
                try {
                    col = collectionClass.newInstance();
                    edges.set(start.getId(), col);
                } catch (InstantiationException | IllegalAccessException exception) {
                    handleCollectionInstantationException(exception);
                    return;
                }
            }

            col.set(end.getId(), edge);
            edgeCount++;
        } catch (NullKey e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEdge(Edge edge) throws EdgeNotFound {
        try {
            edges.get(edge.getStart().getId()).remove(edge.getEnd().getId());
            edgeCount--;
        } catch (KeyNotFound e) {
            throw new EdgeNotFound(edge);
        } catch (NullKey e) {
            e.printStackTrace();
        }
    }

    @Override
    public Edge[] getEdgesIncidentTo(Vertex vertex) throws VertexIdNotFound {
        try {
            if (!vertices.contains(vertex.getId())) {
                throw new VertexIdNotFound(vertex.getId());
            }

            Collection<Integer, Edge> incidentEdges = edges.get(vertex.getId());
            Edge[] list = new Edge[incidentEdges.count()];
            int index = 0;

            for (Edge edge : incidentEdges.getValues(Edge.class)) {
                list[index++] = edge;
            }

            return list;
        } catch (KeyNotFound e) {
            return new Edge[0];
        } catch (NullKey e) {
            e.printStackTrace();
            return new Edge[0];
        }
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    private void handleCollectionInstantationException(Exception exception) {
        exception.printStackTrace();
        System.exit(-1);
    }
}
