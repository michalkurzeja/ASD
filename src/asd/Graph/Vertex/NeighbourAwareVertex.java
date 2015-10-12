package asd.Graph.Vertex;

public interface NeighbourAwareVertex {
    NeighbourAwareVertex[] getNeighbours();
    void addNeighbour(NeighbourAwareVertex vertex);
    boolean removeNeighbour(NeighbourAwareVertex vertex);
}
