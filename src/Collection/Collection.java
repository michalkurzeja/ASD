package Collection;

import Collection.Exception.KeyNotFound;
import Collection.Exception.NullKey;

public interface Collection<K, V> {
    public boolean contains(K key) throws NullKey;
    public V get(K key) throws NullKey, KeyNotFound;
    public Collection<K, V> set(K key, V value) throws NullKey;
    public Collection<K, V> remove(K key) throws NullKey, KeyNotFound;
    public V[] getValues(Class<V> clazz);
    public K[] getKeys(Class<K> clazz);
    public int count();
    public boolean isEmpty();
}
