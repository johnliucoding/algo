package goodrich.ch9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author Liu Xiaodong
 * @since 2024/9/2 11:29 AM
 */
public class IPQueue<E> {

    private final E[] values; // map of key index to value

    private final int[] pm;   // map of key index to position in heap

    private final int[] heap; // map of position in heap to key index

    private final Comparator<E> cmp;

    private final int cap;
    private int size;

    @SuppressWarnings("unchecked")
    public IPQueue(int cap, Comparator<E> cmp) {
        this.size = 0;
        this.cmp = cmp;
        this.cap = cap;

        heap = new int[cap];
        Arrays.fill(heap, -1);
        values = (E[]) new Object[cap];
        pm = new int[cap];
        Arrays.fill(pm, -1);
    }


    public int size() {
        return size;
    }

//    public boolean isEmpty() {
//        return size() == 0;
//    }
//
    public boolean contains(int ki) {
        if (ki < 0 || ki >= cap)
            throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
        return pm[ki] != -1;
    }

    public int peekMinKeyIndex() {
        if (size() == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        return heap[0];
    }

    public int pollMinKeyIndex() {
        final int minKeyIndex = peekMinKeyIndex();
        delete(minKeyIndex);
        return minKeyIndex;
    }

    public E peekMinValue() {
        if (size() == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        return values[heap[0]];
    }

    public E pollMinValue() {
        final E minValue = peekMinValue();
        delete(peekMinKeyIndex());
        return minValue;
    }

    public void insert(int ki, E value) {
        if (contains(ki)) {
            throw new IllegalArgumentException("Key index already exists");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        values[ki] = value;
        pm[ki] = size;
        heap[size] = ki;
        swim(size++);
    }

    public E valueOf(int ki) {
        if (!contains(ki))
            throw new NoSuchElementException("Key index does not exist; received: " + ki);
        return values[ki];
    }

    public E delete(int ki) {
        if (!contains(ki)) {
            throw new NoSuchElementException("Key index not exist; received: " + ki);
        }
        final int i = pm[ki];
        swap(i, --size);
        sink(i);
        swim(i);
        E value = values[ki];
        values[ki] = null;
        pm[ki] = -1;
        heap[size] = -1;
        return value;
    }

    public E update(int ki, E value) {
        if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        final int i = pm[ki];
        final E olderValue = values[ki];
        values[ki] = value;
        sink(i);
        swim(i);
        return olderValue;
    }

    public void decrease(int ki, E value) {
        if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if(less(value, values[ki])) {
            values[ki] = value;
            swim(pm[ki]);
        }
    }

    public void increase(int ki, E value) {
        if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        if (value == null) throw new IllegalArgumentException("value cannot be null");
        if(less( values[ki], value)) {
            values[ki] = value;
            sink(pm[ki]);
        }
    }


    private int minChild(int k) {
        int smallest = - 1;
        int left = 2 * k + 1;
        int right = 2 * k + 2;
        if(left <size && less(left, k)) {
            smallest = left;
            k = left;
        }
        if (right < size && less(right, k)) {
            smallest = right;
            k = right;
        }
        return smallest;
    }

    private void sink(int k) {
        for(int j = minChild(k); j != -1;) {
            swap(k, j);
            k = j;
            j = minChild(j);
        }
    }
    private void swim(int k) {
        int parent = (k - 1) / 2;
        while (k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;
            parent = (k - 1) / 2;
        }
    }

    // k and j are indexes in heap
    private boolean less(int k, int j) {
        E k_value = values[heap[k]];
        E j_value = values[heap[j]];
        return cmp.compare(k_value, j_value) < 0;
    }

    private boolean less(E value1, E value2) {
        return cmp.compare(value1, value2) < 0;
    }

    // k and j are indexes in heap
    private void swap(int k, int j) {
        // pm
        pm[heap[k]] = j;
        pm[heap[j]] = k;
        // heap
        int temp_heap = heap[k];
        heap[k] = heap[j];
        heap[j] = temp_heap;

    }

    @Override
    public String toString() {
        final String heapString = Arrays.toString(heap);
        final String pmStrings = Arrays.toString(pm);
        final String values = Arrays.toString(this.values);
        return heapString + "\n" + pmStrings + "\n" + values;
    }




    /* Test functions */

    // Recursively checks if this heap is a min heap. This method is used
    // for testing purposes to validate the heap invariant.
    public boolean isMinHeap() {
        return isMinHeap(0);
    }

    private boolean isMinHeap(int i) {
        if (i >= size) return true;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && !less(i, left)) {
            return false;
        }
        if (right < size && !less(i, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }
}
