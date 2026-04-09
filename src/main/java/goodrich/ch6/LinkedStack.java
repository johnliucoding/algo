package ch6;

import ch3.SinglyLinkedList;

/**
 * @author Liu Xiaodong
 * @since 2024/8/15 11:36 PM
 */
public class LinkedStack<E> implements Stack<E> {
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedStack() {}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFront(e);
    }

    @Override
    public E pop() {
        return list.removeFront();
    }

    @Override
    public E top() {
        return list.getFirst();
    }
}
