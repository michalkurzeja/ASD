package Graph.Collection;

class Node<T> implements ListNode {
    T data;
    ListNode next;
    ListNode previous;

    Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
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

public class Test {
    public static void main(String[] args) throws Exception {
        List<Node<String>> list = new List<>();

        Node<String> n1 = new Node<>("Node 1");
        Node<String> n2 = new Node<>("Node 2");
        Node<String> n3 = new Node<>("Node 3");

        list.add(n1);
        list.add(n2);
        list.add(n3);

        list.remove(n3);
        list.remove(n1);
//        list.remove(n2);

        for (ListNode n : list) {
            System.out.println(((Node) n).getData());
        }
    }
}
