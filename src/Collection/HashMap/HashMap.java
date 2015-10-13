package Collection.HashMap;

import Collection.Collection;
import Collection.Exception.KeyNotFound;
import Collection.Exception.NullKey;

import java.lang.reflect.Array;

public class HashMap<K, V> implements Collection<K, V> {
    private int SIZE = 50;

    private Node<K, V>[] elements;
    private int nodeCount = 0;

    @SuppressWarnings("unchecked")
    public HashMap() {
        elements = (Node<K, V>[]) new Node[SIZE];
    }

    public Collection<K, V> set(K key, V value) throws NullKey {
        guardAgainstNullKey(key);

        int hash = getHash(key);

        Node<K, V> node = new Node<K, V>(key, value);
        Node<K, V> current = elements[hash];

        if (null == current) {
            elements[hash] = node;
            nodeCount++;

            return this;
        }

        Node<K, V> previous = null;

        while (null != current) {
            if (current.key.equals(key)) {
                if (null == previous) {
                    elements[hash] = node;
                    node.next = current.next;
                    nodeCount++;

                    return this;
                }

                node.next = current.next;
                previous.next = node;
                nodeCount++;

                return this;
            }

            previous = current;
            current = current.next;
        }

        previous.next = node;
        nodeCount++;

        return this;
    }

    @Override
    public boolean contains(K key) throws NullKey {
        guardAgainstNullKey(key);

        int hash = getHash(key);

        if (null == elements[hash]) {
            return false;
        }

        Node <K, V> node = elements[hash];

        while (null != node) {
            if (key.equals(node.key)) {
                return true;
            }

            node = node.next;
        }

        return false;
    }

    public V get(K key) throws NullKey, KeyNotFound {
        guardAgainstNullKey(key);

        int hash = getHash(key);

        if (null == elements[hash]) {
            throw new KeyNotFound(key);
        }

        Node <K, V> node = elements[hash];

        while (null != node) {
            if (key.equals(node.key)) {
                return node.value;
            }

            node = node.next;
        }

        throw new KeyNotFound(key);
    }

    public Collection<K, V> remove(K key) throws NullKey, KeyNotFound {
        guardAgainstNullKey(key);

        int hash = getHash(key);

        Node<K, V> current = elements[hash];

        if (null == current) {
            throw new KeyNotFound(key);
        }

        Node<K, V> previous = null;

        while (null != current) {
            if (key.equals(current.key)) {
                if (null == previous) {
                    elements[hash] = current.next;
                    nodeCount--;

                    return this;
                }

                previous.next = current.next;
                nodeCount--;

                return this;
            }

            previous = current;
            current = current.next;
        }

        throw new KeyNotFound(key);
    }

    @SuppressWarnings("unchecked")
    public V[] getValues(Class<V> clazz) {
        V[] values = (V[]) Array.newInstance(clazz, nodeCount);
        int index = 0;

        for (Node<K, V> headNode : elements) {
            Node<K, V> current = headNode;

            while (null != current) {
                values[index++] = current.value;
                current = current.next;
            }
        }

        return values;
    }

    @SuppressWarnings("unchecked")
    public K[] getKeys(Class<K> clazz) {
        K[] keys = (K[]) Array.newInstance(clazz, nodeCount);
        int index = 0;

        for (Node<K, V> headNode : elements) {
            Node<K, V> current = headNode;

            while (null != current) {
                keys[index++] = current.key;
                current = current.next;
            }
        }

        return keys;
    }

    @Override
    public int count() {
        return nodeCount;
    }

    @Override
    public boolean isEmpty() {
        return nodeCount == 0;
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    private void guardAgainstNullKey(K key) throws NullKey {
        if (null == key) {
            throw new NullKey();
        }
    }
}
