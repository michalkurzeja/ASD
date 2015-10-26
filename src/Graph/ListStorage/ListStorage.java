package Graph.ListStorage;

import Graph.Collection.List;
import Graph.Edge;
import Graph.Storage;
import Graph.Vertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class ListStorage<V> implements Storage<V> {

    private List<LSVertex<V>> vertices = new List<>();
    int vertexCount = 0;
    int edgeCount = 0;

    @Override
    public Vertex findVertex(V data) {
        for (LSVertex<V> vertex : vertices) {
            if (vertex.getData().equals(data)) {
                return vertex;
            }
        }

        return null;
    }

    @Override
    public Vertex addVertex(V data) {
        LSVertex<V> vertex = new LSVertex<>(data);
        vertices.add(vertex);
        vertexCount++;

        return vertex;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeVertex(Vertex<V> vertex) {
        edgeCount -= getEdgesIncidentTo(vertex).length;

        for (Edge edge : ((LSVertex<V>) vertex).getOutgoingEdges()) {   // clear outgoing edges
            removeEdge((Edge<Vertex<V>>) edge);
        }
        ((LSVertex<V>) vertex).getOutgoingEdges().clear();

        Edge edge;
        for (Edge incomingEdge : ((LSVertex<V>) vertex).getIncomingEdges()) {   // for each copy find original incoming edge and remove it
            edge = getEdge(incomingEdge.getStart(), incomingEdge.getEnd());
            removeEdge(edge);
        }
        ((LSVertex<V>) vertex).getIncomingEdges().clear();

        vertices.remove((LSVertex<V>) vertex);
        vertexCount--;
    }

    @Override
    public Edge getEdge(Vertex<V> start, Vertex<V> end) {
        for (LSEdge edge : ((LSVertex<V>) start).getOutgoingEdges()) {
            if (edge.getEnd().equals(end)) {
                return edge;
            }
        }

        return null;
    }

    @Override
    public Edge addEdge(Vertex<V> start, Vertex<V> end, int weight) {
        LSEdge<LSVertex<V>> edge = new LSEdge<>((LSVertex<V>) start, (LSVertex<V>) end, weight);
        ((LSVertex<V>) start).getOutgoingEdges().add(edge);
        ((LSVertex<V>) end).getIncomingEdges().add(new LSEdge<>(edge));

        edgeCount++;

        return edge;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeEdge(Edge<Vertex<V>> edge) {
        ((LSVertex<V>) edge.getStart()).getOutgoingEdges().remove((LSEdge) edge);

        List<LSEdge<LSVertex<V>>> incomingEdges = ((LSVertex<V>) edge.getEnd()).getIncomingEdges();

        for (LSEdge e : incomingEdges) {    // remove copies of 'edge' from incoming edges list
            if (e.isCopyOf((LSEdge) edge)) {
                incomingEdges.remove(e);
            }
        }

        edgeCount--;
    }

    @Override
    public Vertex[] getNeighboursOf(Vertex<V> vertex) {
        LinkedList<Vertex> vertices = new LinkedList<>();

        for (Edge edge : ((LSVertex<V>) vertex).getOutgoingEdges()) {
            vertices.add(edge.getEnd());
        }

        return vertices.toArray(new Vertex[vertices.size()]);
    }

    @Override
    public Edge[] getEdgesIncidentTo(Vertex<V> vertex) {
        LinkedList<Edge> edges = new LinkedList<>();

        for (Edge edge : ((LSVertex<V>) vertex).getOutgoingEdges()) {
            edges.add(edge);
        }

        Edge edge;
        for (LSEdge<LSVertex<V>> edgeCopy : ((LSVertex<V>) vertex).getIncomingEdges()) {
            edge = getEdge(edgeCopy.getStart(), edgeCopy.getEnd());
            edges.add(edge);
        }

        return edges.toArray(new Edge[edges.size()]);
    }

    @Override
    public Edge[] getEdgesOutgoingFrom(Vertex<V> vertex) {
        LinkedList<Edge> edges = new LinkedList<>();

        for (Edge edge : ((LSVertex<V>) vertex).getOutgoingEdges()) {
            edges.add(edge);
        }

        return edges.toArray(new Edge[edges.size()]);
    }

    @Override
    public Vertex[] getVertices() {
        LinkedList<Vertex> vertices = new LinkedList<>();

        for (Vertex vertex : this.vertices) {
            vertices.add(vertex);
        }

        return vertices.toArray(new Vertex[vertices.size()]);
    }

    @Override
    public Edge[] getEdges() {
        HashMap<Edge, Edge> edgeMap = new HashMap<>();

        for (LSVertex<V> vertex : this.vertices) {
            for (LSEdge<LSVertex<V>> e : vertex.getOutgoingEdges()) {
                edgeMap.put(e, e);
            }
        }

        Collection<Edge> edgeCollection = edgeMap.values();

        return edgeCollection.toArray(new Edge[edgeCollection.size()]);
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }
}
