import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V > implements Map61B<K, V> {
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private int size;
    private Node<K, V> root;

    public BSTMap() {
        this.size = 0;
        this.root = null;
    }


    @Override
    public void put(K key, V  value) {
        int lastmove = 0;
        Node<K, V> lastroot = root;
        if (this.size == 0){
            this.root = new Node<>(key, value);
            this.size = 1;
        } else {
            this.size++;
            Node<K, V> currentroot = root;
            while (currentroot != null) {
                int cmp = key.compareTo(currentroot.key);
                if (cmp < 0){
                    if (currentroot.left == null){
                        currentroot.left = new Node<>(key, value);
                        return;
                    }
                    lastmove = 0; // 0 for left
                    lastroot = currentroot;
                    currentroot = currentroot.left;
                }
                if (cmp > 0){
                    if (currentroot.right == null){
                        currentroot.right = new Node<>(key, value);
                        return;
                    }
                    lastmove = 1; // 1 for right
                    lastroot = currentroot;
                    currentroot = currentroot.right;
                }
                if (cmp == 0){
                    this.size--;
                    if (lastmove == 0) {
                        lastroot.left = new Node<>(key, value);
                    } else {
                        lastroot.right = new Node<>(key, value);
                    }
                    return;
                }
            }
        }

    }

    @Override
    public V get(K key) {
        Node<K, V> currentroot = root;
        while (currentroot != null){
            int cmp = key.compareTo(currentroot.key);
            if (cmp < 0){
                currentroot = currentroot.left;
            } else if (cmp > 0){
                currentroot = currentroot.right;
            }  else {
                return currentroot.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> currentroot = root;
        while (currentroot != null){
            int cmp = key.compareTo(currentroot.key);
            if (cmp < 0){
                currentroot = currentroot.left;
            } else if (cmp > 0){
                currentroot = currentroot.right;
            }  else {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public Set<K> keySet() {
        throw new RuntimeException("UnsupportedOperationException");
    }

    @Override
    public V remove(K key) {
        throw new RuntimeException("UnsupportedOperationException");
    }

    @Override
    public Iterator<K> iterator () {
        return null;
    }
}
