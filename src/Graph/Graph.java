package Graph;

import java.util.*;

public class Graph<V> {
    Storage<V> storage;

    public Graph(Storage<V> storage) {
        this.storage = storage;
    }

    public Vertex findVertex(V data) {
        return storage.findVertex(data);
    }

    public Vertex addVertex(V data) {
        return storage.addVertex(data);
    }

    public void removeVertex(Vertex<V> vertex) {
        storage.removeVertex(vertex);
    }

    public Edge getEdge(Vertex<V> start, Vertex<V> end) {
        return storage.getEdge(start, end);
    }

    public void addEdge(Vertex<V> start, Vertex<V> end) {
        storage.addEdge(start, end, Edge.DEFAULT_WEIGHT);
    }

    public Edge addEdge(Vertex<V> start, Vertex<V> end, float weight) {
        return storage.addEdge(start, end, weight);
    }

    public void removeEdge(Edge<Vertex<V>> edge) {
        storage.removeEdge(edge);
    }

    public Vertex[] getNeighboursOf(Vertex<V> vertex) {
        return storage.getNeighboursOf(vertex);
    }

    public Edge[] getEdgesIncidentTo(Vertex<V> vertex) {
        return storage.getEdgesIncidentTo(vertex);
    }

    public boolean areNeighbours(Vertex<V> v1, Vertex<V> v2) {
        Vertex[] neighbours = getNeighboursOf(v1);

        for (Vertex neighbour : neighbours) {
            if (neighbour.equals(v2)) {
                return true;
            }
        }

        return false;
    }

    public Vertex[] getVertices() {
        return storage.getVertices();
    }

    public int getVertexCount() {
        return storage.getVertexCount();
    }

    public int getEdgeCount() {
        return storage.getEdgeCount();
    }

    @SuppressWarnings("unchecked")
    public Edge[] findPath(Vertex<V> start, Vertex<V> end) {
        float INFINITY = -1;
        HashMap<Vertex<V>, Float> distance = new HashMap<>();
        HashMap<Vertex<V>, Vertex> parents = new HashMap<>();
        LinkedList<Vertex<V>> Q = new LinkedList<>();
        Vertex<V> current;

        for (Vertex<V> vertex : getVertices()) {
            distance.put(vertex, INFINITY);
            parents.put(vertex, null);
        }

        distance.put(start, (float) 0);
        Q.add(start);

        while (!Q.isEmpty()) {
            current = Q.pop();

            for (Vertex neighbour : getNeighboursOf(current)) {
                if (distance.get(neighbour) == INFINITY) {
                    distance.put(neighbour, distance.get(current) + getEdge(current, neighbour).getWeight());
                    parents.put(neighbour, current);
                    Q.add(neighbour);
                }
            }
        }

        LinkedList<Edge> path = new LinkedList<>();
        current = end;
        Vertex parent = parents.get(current);

        while (null != parent) {
            path.push(getEdge(parent, current));
            current = parent;
            parent = parents.get(current);
        }

        return path.toArray(new Edge[path.size()]);
    }
}
