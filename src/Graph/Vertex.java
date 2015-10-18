package Graph;

public abstract class Vertex<V> {
    private V data;

    public Vertex(V data) {
        this.data = data;
    }

    public V getData() {
        return data;
    }
}
