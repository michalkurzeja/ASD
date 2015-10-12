package asd.Graph.Vertex;

public class Vertex {
    private static int next_id = 0;

    private int id;

    public Vertex() {
        id = next_id++;
    }

    public int getId() {
        return id;
    }
}
