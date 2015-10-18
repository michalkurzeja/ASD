package Graph.MatrixStorage;

import Graph.Collection.DynamicArray;
import Graph.Edge;
import Graph.Storage;
import Graph.Vertex;

import java.util.LinkedList;

public class MatrixStorage<V> implements Storage<V> {

    private DynamicArray<MSVertex<V>> vertices = new DynamicArray<>();
    private DynamicArray<DynamicArray<Edge>> edges = new DynamicArray<>();
    int vertexCount = 0;
    int edgeCount = 0;

    @Override
    public Vertex findVertex(V data) {
        for (int i = 0; i < vertexCount; i++) {
            if (vertices.get(i).getData().equals(data)) {
                return vertices.get(i);
            }
        }

        return null;
    }

    @Override
    public Vertex addVertex(V data) {
        MSVertex<V> vertex = new MSVertex<>(vertices.getNextIndex(), data);
        vertices.add(vertex);

        vertexCount++;

        for (int i = 0; i < vertexCount - 1; i++) { // add new column to existing rows
            edges.get(i).add(null);
        }

        edges.add(new DynamicArray<>(vertexCount)); // add new row

        return vertex;
    }

    @Override
    public void removeVertex(Vertex<V> vertex) {
        int index = ((MSVertex<V>) vertex).getId();

        edgeCount -= getEdgesIncidentTo(vertex).length;

        for (int i = 0; i < vertexCount; i++) { // remove 'index' column of existing rows
            edges.get(i).remove(index);
        }

        edges.remove(index);    // remove 'index' row

        vertices.remove(index);
        vertexCount--;

        for (int i = 0; i < vertexCount; i++) {    // reindex vertices
            vertices.get(i).setId(i);
        }
    }

    @Override
    public Edge getEdge(Vertex<V> start, Vertex<V> end) {
        return edges.get(
            ((MSVertex<V>) start).getId()
        ).get(
            ((MSVertex<V>) end).getId()
        );
    }

    @Override
    public Edge addEdge(Vertex<V> start, Vertex<V> end, float weight) {
        Edge edge = new Edge<Vertex<V>>(start, end, weight);

        edges.get(
            ((MSVertex<V>) start).getId()
        ).set(
            ((MSVertex<V>) end).getId(), edge
        );

        edgeCount++;

        return edge;
    }

    @Override
    public void removeEdge(Edge<Vertex<V>> edge) {
        edges.get(
                ((MSVertex<V>) edge.getStart()).getId()
        ).set(
                ((MSVertex<V>) edge.getEnd()).getId(), null
        );

        edgeCount--;
    }

    @Override
    public Vertex[] getNeighboursOf(Vertex<V> vertex) {
        int index = ((MSVertex<V>) vertex).getId();
        Edge edge;
        LinkedList<Vertex> neighbourVertices = new LinkedList<>();

        for (int i = 0; i < vertexCount; i++) {
            edge = edges.get(index).get(i);

            if (null != edge) {
                neighbourVertices.add(edge.getEnd());
            }
        }

        return neighbourVertices.toArray(new Vertex[neighbourVertices.size()]);
    }

    @Override
    public Edge[] getEdgesIncidentTo(Vertex<V> vertex) {
        int index = ((MSVertex<V>) vertex).getId();
        Edge edge;
        LinkedList<Edge> incidentEdges = new LinkedList<>();

        for (int i = 0; i < vertexCount; i++) {
            edge = edges.get(index).get(i); // outgoing edge

            if (null != edge && !incidentEdges.contains(edge)) {
                incidentEdges.add(edge);
            }

            edge = edges.get(i).get(index); // incoming edge

            if (null != edge && !incidentEdges.contains(edge)) {
                incidentEdges.add(edge);
            }
        }

        return incidentEdges.toArray(new Edge[incidentEdges.size()]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Vertex[] getVertices() {
        return vertices.getArray(new MSVertex[vertexCount]);
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
