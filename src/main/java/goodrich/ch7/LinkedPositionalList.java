package ch7;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 7:13 PM
 */
public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {

    private static class Node<E> implements Position<E> {
         E element;
         Node<E> next;
         Node<E> prev;
        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
        @Override
        public E getElement() throws IllegalStateException {
            if(next == null) { // convention for defunct node
                throw new IllegalStateException("Position is no longer valid");
            }
            return element;
        }
    }

    private final Node<E> head;
    private final Node<E> tail;
    private int size = 0;
    public LinkedPositionalList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, null, head);
        head.next = tail;
    }

    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof LinkedPositionalList.Node)) {
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>) p;
        if(node.next == null) {
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    private Position<E> position(Node<E> node) {
        if(node == head || node == tail) {
            return null;
        }
        return node;
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
    public Position<E> first() {
        return position(head.next);
    }

    @Override
    public Position<E> last() {
        return position(tail.prev);
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return position(node.prev);
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return position(node.next);
    }

    private Position<E> addBetween(E e, Node<E> prev, Node<E> suc) {
        final Node<E> node = new Node<>(e, suc, prev);
        prev.next = node;
        suc.prev = node;
        size++;
        return node;
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, head, head.next);
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, tail.prev, tail);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return addBetween(e, node.prev, node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        return addBetween(e, node, node.next);
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        E ans = node.element;
        node.element = e;
        return ans;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        final Node<E> node = validate(p);
        final Node<E> prev = node.prev;
        final Node<E> next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
        final E ans = node.element;
        node.element = null;
        node.next = null;
        node.prev = null;
        return ans;
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;


        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if(cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws IllegalStateException {
            if(recent == null) {
                throw new IllegalStateException("nothing to remvoe");
            }
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }
    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();


        @Override
        public boolean hasNext() {
            return posIterator.hasNext();
        }

        @Override
        public E next() {
            return posIterator.next().getElement();
        }

        @Override
        public void remove() {
            posIterator.remove();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
