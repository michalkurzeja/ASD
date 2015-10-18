package Graph.ListStorage;

import Graph.Collection.ListNode;
import Graph.Edge;
import Graph.Vertex;

class LSEdge<V extends Vertex> extends Edge<V> implements ListNode {

    private ListNode next;
    private ListNode previous;

    public LSEdge(V start, V end) {
        super(start, end);
    }

    public LSEdge(V start, V end, float weight) {
        super(start, end, weight);
    }

    public LSEdge(LSEdge<V> edge) {
        super(edge.getStart(), edge.getEnd(), edge.getWeight());
    }

    boolean isCopyOf(LSEdge<V> edge) {
        return getStart().equals(edge.getStart())
                && getEnd().equals(edge.getEnd())
                && getWeight() == edge.getWeight();
    }

    @Override
    public ListNode getNext() {
        return next;
    }

    @Override
    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public ListNode getPrevious() {
        return previous;
    }

    @Override
    public void setPrevious(ListNode previous) {
        this.previous = previous;
    }
}
