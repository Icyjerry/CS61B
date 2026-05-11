import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private Node sentinel;
    private int size;
    private class Node{
        private T item;
        private Node next;
        private Node prev;
        public Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node current = sentinel.next;
        while (current != sentinel){
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        Node removed = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return removed.item;
    }

    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        Node removed = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return removed.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size){
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;

    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(Node current,int index){
        if (index == 0){
            return current.item;
        } else {
            return getRecursiveHelper(current.next, index-1);
        }
    }

}

