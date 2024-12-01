package org.example;

public interface Queue<T> {
    void enqueue(T item);
    T dequeue();
    boolean isEmpty();
    int size();
}

public class ArrayQueue<T> implements Queue<T> {
    private List<T> items = new ArrayList<>();

    @Override
    public void enqueue(T item) {
        items.add(item);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return items.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public int size() {
        return items.size();
    }
}
