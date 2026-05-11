import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private int size;
    private int capacity;
    private T[] list;
    private int head;
    private int tail;
    public ArrayDeque61B() {
        this.size = 0;
        this.capacity = 8;
        this.list = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
    }
    @Override
    public void addFirst(T x) {
        if (capacity == size) {
            resize(2);
        }
        size++;
        head = (head + capacity - 1) % capacity;
        list[head] = x;
    }

    @Override
    public void addLast(T x) {
        if (capacity == size) {
            resize(2);
        }
        size++;
        list[tail] = x;
        tail++;
    }

    @Override
    public List<T> toList() {
        int current = head;
        List<T> result = new ArrayList<>(size);
        while (current != tail) {
            result.add(list[current]);
            current = (current + 1) % capacity;
        }
        return result;
    }

    public void resize(double coefficient){
        T[] newList = (T[]) new Object[(int)(capacity * coefficient)];
        int current = head;
        int count = 0;
        while (count == 0 || current != tail) {
            newList[count++] = list[current];
            current = (current + 1) % capacity;
        }
        head = 0;
        tail = size;
        capacity = (int)(capacity * coefficient);
        list = newList;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removed = list[head];
        list[head] = null;
        head = (head + 1) % capacity;
        size--;
        if (size <= capacity / 4 && size > 0) {
            resize(0.5);
        }
        return removed;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removed = list[(tail - 1 + capacity) % capacity];
        list[(tail - 1 + capacity) % capacity] = null;
        tail = (tail - 1) % capacity;
        size--;
        if (size <= capacity / 4 && size >0) {
            resize(0.5);
        }
        return removed;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size){
            return null;
        }
        return list[(head + index + capacity) % capacity];
    }

    @Override
    public T getRecursive(int index) {
        return(this.get(index));
    }
}
