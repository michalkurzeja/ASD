package Collection.HashMap;

class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(K key, V value, Node<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
