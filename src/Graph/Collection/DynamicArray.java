package Graph.Collection;

import java.lang.reflect.Array;

public class DynamicArray<V> {
    private V[] array;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public DynamicArray() {
        array = (V[]) new Object[0];
    }

    @SuppressWarnings("unchecked")
    public DynamicArray(int size) {
        array = (V[]) new Object[size];
        this.size = size;
    }

    public V get(int index) {
        if (!isIndexValid(index)) {
            return null;
        }

        return array[index];
    }

    public boolean set(int index, V value) {
        if (!isIndexValid(index)) {
            return false;
        }

        array[index] = value;

        return true;
    }

    @SuppressWarnings("unchecked")
    public void add(V value) {
        V[] newArray = (V[]) new Object[size+1];

        System.arraycopy(array, 0, newArray, 0, size);
        newArray[size] = value;

        array = newArray;
        size++;
    }

    @SuppressWarnings("unchecked")
    public boolean remove(int index) {
        if (!isIndexValid(index)) {
            return false;
        }

        V[] newArray = (V[]) new Object[size+1];

        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index+1, newArray, index, size - index-1);

        array = newArray;
        size--;

        return true;
    }

    public boolean removeElement(V value) {
        return remove(indexOf(value));
    }

    public int indexOf(V value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(V value) {
        return indexOf(value) != -1;
    }

    public int getSize() {
        return size;
    }

    public int getNextIndex() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public V[] getArray(V[] a) {
        System.arraycopy(array, 0, a, 0, size);

        return a;
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }
}
