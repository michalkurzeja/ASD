package asd.Graph.Edge;

import asd.Graph.Vertex.Vertex;

public class Edge {
    private static int next_id = 0;

    private int id;
    private Vertex start;
    private Vertex end;
    private float weight = 1;

    public Edge(Vertex start, Vertex end) {
        id = next_id++;
        this.start = start;
        this.end = end;
    }

    public Edge(Vertex start, Vertex end, float weight) {
        id = next_id++;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
