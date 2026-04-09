package goodrich.ch6;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 2:25 PM
 */
public class ArrayQueue<E> implements Queue<E> {

    private static int CAPACITY = 100;

    private E[] data;
    private int head = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }
    public ArrayQueue() {
        this(CAPACITY);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean offer(E e) {
        if (size == data.length) {
            return false;
        }
        int avail = (head + size) % data.length;
        data[avail] = e;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) return null;
        E ans = data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return ans;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;
        return data[head];
    }
}
