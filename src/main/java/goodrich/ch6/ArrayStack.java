package goodrich.ch6;

/**
 * @author Liu Xiaodong
 * @since 2024/8/15 11:19 PM
 */
public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY = 1000;
    private final E[] data;
    private int t = -1;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayStack() {
        this(CAPACITY);
    }


    @Override
    public int size() {
        return (t + 1);
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E ans = data[t];
        data[t] = null;
        t--;
        return ans;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }
}
