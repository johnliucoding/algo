package goodrich.ch6;

import goodrich.ch3.CircularlyLinkedList;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 2:51 PM
 */
public class LinkedQueue<E> implements CircularlyQueue<E> {

    private final CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

    public LinkedQueue() {

    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean offer(E e) {
        list.addLast(e);
        return true;
    }

    @Override
    public E poll() {
        return list.removeFont();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public void rotate() {
        list.rotate();
    }
}
