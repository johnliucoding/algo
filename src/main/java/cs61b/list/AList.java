package cs61b.list;

import java.util.Arrays;

public class AList<T> implements List<T> {

    private T[] buffer;

    private int cap;

    private int size;

    @SuppressWarnings("unchecked")
    public AList() {
        this.cap = 10;
        this.buffer = (T[]) new Object[cap];
        this.size = 0;
    }

    @Override
    public void addFirst(T x) {
        insert(x, 0);
    }

    public void addLast(T item) {
        if(size >= cap) {
            resize();
        }
        buffer[size] = item;
        size++;
    }

    @Override
    public T getFirst() {
        return buffer[0];
    }
    @Override
    public T getLast() {
        if(size == 0) {
            throw  new RuntimeException("empty");
        }
        return buffer[size-1];
    }
    @Override
    public T removeLast() {
        if(size == 0) {
            throw  new RuntimeException("empty");
        }
        final T item = buffer[size - 1];
        buffer[size-1] = null;
        size--;
        return item;
    }

    @Override
    public T get(int i) {
        if(i < 0 || i >= size) {
            throw new RuntimeException("out of range");
        }
        return buffer[i];
    }

    @Override
    public void insert(T x, int p) {
        if(size >= cap) {
            resize();
        }
        System.arraycopy(buffer, p, buffer, p + 1, size - p);
        buffer[p] = x;
        size++;
    }


    @Override
    public int size() {
        return size;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        System.out.println("resize");

        int newCap = cap * 3 / 2;
        final T[] newBuffer = (T[]) new Object[newCap];
        System.arraycopy(buffer, 0, newBuffer, 0, size);

        this.cap = newCap;
        this.buffer = newBuffer;
    }

    @Override
    public String toString() {
        return "AList{" +
                "buffer=" + Arrays.toString(buffer) +
                ", cap=" + cap +
                ", size=" + size +
                '}';
    }
}
