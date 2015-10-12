package asd.Graph.Exception;

import asd.Graph.Edge.Edge;

public class EdgeNotFound extends Exception {
    public EdgeNotFound(Edge edge) {
        super(String.format("Edge between Vertices ID '%d' and '%d' was not found", edge.getStart().getId(), edge.getEnd().getId()));
    }
}
