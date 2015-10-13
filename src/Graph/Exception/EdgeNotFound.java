package Graph.Exception;

import Graph.Edge.Edge;

public class EdgeNotFound extends Exception {
    public EdgeNotFound() {
        super("Edge was not found");
    }

    public EdgeNotFound(Edge edge) {
        super(String.format("Edge between Vertices ID '%d' and '%d' was not found", edge.getStart().getId(), edge.getEnd().getId()));
    }
}
