package Graph;

public class Edge<V extends Vertex> {
    static final public int DEFAULT_WEIGHT = 1;

    private V start;
    private V end;
    private int weight = DEFAULT_WEIGHT;

    public Edge(V start, V end) {
        this.start = start;
        this.end = end;
    }

    public Edge(V start, V end, int weight) {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
