package Graph;

public class Edge<V extends Vertex> {
    static final public float DEFAULT_WEIGHT = 1;

    private V start;
    private V end;
    private float weight = DEFAULT_WEIGHT;

    public Edge(V start, V end) {
        this.start = start;
        this.end = end;
    }

    public Edge(V start, V end, float weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public V getStart() {
        return start;
    }

    public V getEnd() {
        return end;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
