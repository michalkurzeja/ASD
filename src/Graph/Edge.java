package Graph;

public class Edge {
    static final public float DEFAULT_WEIGHT = 1;

    private Vertex start;
    private Vertex end;
    private float weight = DEFAULT_WEIGHT;

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }

    public Edge(Vertex start, Vertex end, float weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
