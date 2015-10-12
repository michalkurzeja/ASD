package asd.Graph.Exception;

public class VertexIdNotFound extends Exception {
    public VertexIdNotFound(int id) {
        super(String.format("Vertex ID '%s' was not found", id));
    }
}
