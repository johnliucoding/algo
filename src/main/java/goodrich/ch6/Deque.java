package ch6;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 4:19 PM
 */
public interface Deque<E> {
    int size();
    boolean isEmpty();
    E first();
    E last();
    void addFirst(E e);
    void addLast(E e);
    E removeFirst();
    E removeLast();
}
