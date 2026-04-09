package ch6;

/**
 * @author Liu Xiaodong
 * @since 2024/8/17 6:12 PM
 */
public interface Queue<E> {

    int size();

    boolean isEmpty();

    boolean offer(E e);

    E poll();

    E peek();
}
