package Graph.ListStorage;

import Graph.Collection.List;
import Graph.Collection.ListNode;
import Graph.Vertex;

class LSVertex<V> extends Vertex<V> implements ListNode {
    private List<LSEdge<LSVertex<V>>> outgoingEdges = new List<>();
    private List<LSEdge<LSVertex<V>>> incomingEdges = new List<>();
    private ListNode next;
    private ListNode previous;

    LSVertex(V data) {
        super(data);
    }

    List<LSEdge<LSVertex<V>>> getOutgoingEdges() {
        return outgoingEdges;
    }

    List<LSEdge<LSVertex<V>>> getIncomingEdges() {
        return incomingEdges;
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

