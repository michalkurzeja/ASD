package Graph.Collection;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class List<Node extends ListNode> implements Iterable<Node> {
    
    private Node head = null;
    private Node tail = null;
    private int length = 0;

    public void add(Node node) {
        if (null == tail) {
            head = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
        }

        tail = node;
        length++;
    }

    public boolean remove(Node node) {
        if (null == node.getNext() && null == node.getPrevious()) {
            return false;
        }

        if (head == null) { // empty list
            return false;
        }

        if (node == head && head == tail) { // 1-element list
            head = null;
            tail = null;
        } else  {
            if (node == head) { // 1st element
                head = (Node) node.getNext();
                head.setPrevious(null);
            } else if (node == tail) {  // last element
                tail = (Node) node.getPrevious();
                tail.setNext(null);
            } else {    // element in the middle
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
            }
        }

        node.setNext(null);
        node.setPrevious(null);

        length--;
        return true;
    }

    public void clear() {
        head = null;
        tail = null;
        length = 0;
    }

    public int getLength() {
        return length;
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            ListNode current = head;

            @Override
            public boolean hasNext() {
                return null != current;
            }

            @Override
            public Node next() {
                Node node = (Node) current;
                current = current.getNext();
                return node;
            }
        };
    }
}
