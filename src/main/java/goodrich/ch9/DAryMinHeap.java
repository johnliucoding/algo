package goodrich.ch9;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2024/9/3 12:26 AM
 */
public class DAryMinHeap<E extends Comparable<E>> {
    private final static int EXPAND_RATIO = 2;
    private final static double COLLAPSE_RATIO = 0.25;

    private E[] arr;
    private int d;
    private int size;
    private int initialSize;

    public DAryMinHeap(int initialSize, int d) {
        if (d < 2) {
            throw new IllegalArgumentException("D must be at least 2");
        }
        this.d = d;
        this.initialSize = initialSize;
        this.size = 0;

        this.arr = (E[]) new Object[initialSize];
    }

    public void insert(E e) {
        if (arr.length == size) {
            expand();
        }
        size++;
        int index = size - 1;
        swim(index, e);
    }

    private void swim(int index, E e) {
        int parentIndex = getParentIndex(index);
        while (index != 0 && e.compareTo(arr[parentIndex]) < 0) {
            arr[index] = arr[parentIndex];
            index = parentIndex;
            parentIndex = getParentIndex(index);
        }
        arr[index] = e;
    }

    private int getParentIndex(int index) {
        return (index - 1) / d;
    }

    private void expand() {

        arr = Arrays.copyOf(arr, arr.length * EXPAND_RATIO);
    }

    public E extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        final E temp = arr[0];
        arr[0] = arr[size - 1];
        size--;
        if (size < arr.length * COLLAPSE_RATIO &&
                arr.length / EXPAND_RATIO > initialSize) {
            collapse();
        }
        sink(0, arr[0]);
        return temp;
    }

    private void sink(int index, E e) {
        int succ = findSucc(index * d + 1, index * d + d);
        while (succ < size && e.compareTo(arr[succ]) > 0) {
            arr[index] = arr[succ];
            index = succ;
            succ = findSucc(succ * d + 1, succ * d + d);
        }
        arr[index] = e;
    }

    private int findSucc(int from, int to) {
        int succ = from;
        for (int i = from+1; i <= to && i < size; i++) {
            if(arr[succ].compareTo(arr[i]) > 0) {
                succ = i;
            }
        }
        return succ;
    }

    private void collapse() {
        arr = Arrays.copyOf(arr, arr.length / EXPAND_RATIO);
    }
}
