package ch7;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Liu Xiaodong
 * @since 2024/8/18 6:15 PM
 */
public class ArrayList<E> implements List<E>, Iterable<E> {

    public static final int DEFAULT_CAPACITY = 16;
    private E[] elements;
    private int size;
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if(i < 0 || i > n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    @SuppressWarnings("unchecked")
    protected void resize(int newCapacity) {
        E[] newElements = (E[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        return elements[index];
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        E tmp = elements[index];
        elements[index] = element;
        return tmp;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        checkIndex(index, size+1);
        if(size == elements.length) {
            resize(elements.length * 2);
        }
        for(int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        checkIndex(index, size);
        E tmp = elements[index];
        for(int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return tmp;
    }

    private class ArrayIterator implements Iterator<E> {
        private int i = 0; // next element to return
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if(i == size) throw new NoSuchElementException("No next element");
            removable = true;
            return elements[i++];
        }

        @Override
        public void remove() throws IllegalStateException {
            if(!removable) throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(i-1);
            i--;
            removable = false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
