package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection[] buckets;
    private int size = 0;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int initialCapacity = 16;
    private double loadFactor;


    /** Constructors */
    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity must be > 0");
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("loadFactor must be > 0");
        }
        this.buckets = new Collection[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;

    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    private void resize(int newCapacity) {
        Collection<Node>[] newBuckets = (Collection<Node>[]) new Collection[newCapacity];
        for (Collection bucket : buckets) {
            if (bucket == null) continue;
            for (Object o : bucket) {
                Node node = (Node) o;
                int newIndex = Math.floorMod(node.key.hashCode(), newCapacity);
                if (newBuckets[newIndex] == null) {
                    newBuckets[newIndex] = createBucket();
                }
                newBuckets[newIndex].add(node);
            }
        }

        this.buckets = newBuckets;
    }

    @Override
    public void put(K key, V value) {
        int hash = key.hashCode();
        int index = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[index] == null) {
            buckets[index] = createBucket();
        }
        for (Object o : buckets[index]) {
            Node node = (Node) o;
            if (node.key.equals(key)) {
                V oldVal = node.value;
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node(key, value));
        size++;
        if (size > buckets.length * loadFactor){
            resize(buckets.length * 2);
        }

    }

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int index = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[index] == null) {
            return null;
        }
        for (Object o : buckets[index]){
            Node node = (Node) o;
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[index] == null) return false;
        for (Object o : buckets[index]) {
            Node node = (Node) o;
            if (node.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        buckets = new Collection[16];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
