package Collection.ArrayCollection;

import Collection.Collection;
import Collection.Exception.KeyNotFound;
import Collection.Exception.NullKey;

import java.lang.reflect.Array;

public class ArrayCollection<K extends Integer, V> implements Collection<K, V> {
    private Element<K, V>[] elements;

    private int count = 0;

    @SuppressWarnings("unchecked")
    public ArrayCollection() {
        elements = (Element<K, V>[]) new Element[0];
    }

    @Override
    public boolean contains(K key) throws NullKey {
        guardAgainstNullKey(key);

        return null != getElement(key);
    }

    @Override
    public V get(K key) throws NullKey, KeyNotFound {
        guardAgainstNullKey(key);

        Element<K, V> element = getElement(key);

       if (null != element) {
           return element.value;
       }

        throw new KeyNotFound(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<K, V> set(K key, V value) throws NullKey {
        guardAgainstNullKey(key);

        Element<K, V> element = getElement(key);

        if (null != element) {
            element.value = value;
            return this;
        }

        element = new Element<>(key, value);
        Element<K, V>[] newElements = (Element<K, V>[]) new Element[count+1];

        System.arraycopy(elements, 0, newElements, 0, count);
        newElements[count] = element;

        elements = newElements;
        count++;

        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<K, V> remove(K key) throws NullKey, KeyNotFound {
        guardAgainstNullKey(key);

        if (isEmpty()) {
            throw new KeyNotFound(key);
        }

        int index = 0;

        do {
            Element<K, V> element = elements[index];

            if (key.equals(element.key)) {
                Element<K, V>[] newElements = (Element<K, V>[]) new Element[count-1];

                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index+1, newElements, index, count - index-1);

                elements = newElements;
                count--;

                return this;
            }
        } while (++index < count);

        throw new KeyNotFound(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V[] getValues(Class<V> clazz) {
        V[] values = (V[]) Array.newInstance(clazz, count);

        for (int index = 0; index < count; index++) {
            values[index] = elements[index].value;
        }

        return values;
    }

    @Override
    @SuppressWarnings("unchecked")
    public K[] getKeys(Class<K> clazz) {
        K[] keys = (K[]) Array.newInstance(clazz, count);

        for (int index = 0; index < count; index++) {
            keys[index] = elements[index].key;
        }

        return keys;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    private Element<K, V> getElement(K key) {
        for (Element<K, V> element : elements) {
            if (element.key.equals(key)) {
                return element;
            }
        }

        return null;
    }

    private void guardAgainstNullKey(K key) throws NullKey {
        if (null == key) {
            throw new NullKey();
        }
    }
}
