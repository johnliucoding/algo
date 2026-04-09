package goodrich.ch9.heap;

import java.util.ArrayList;

/**
 * @author Liu Xiaodong
 * @since 2025/2/28 11:25 AM
 */
public class MinDHeap<E extends Comparable<E>> {

    private final ArrayList<E> heap;
    private int degree;

    public MinDHeap(int degree) {
        this.heap = new ArrayList<>();
        this.degree = degree;
    }

    public int size() {
        return heap.size();
    }

    public E peak() {
        if (size() == 0) {
            return null;
        }
        return heap.get(0);
    }

    public E poll() {
        if (size() == 0) {
            return null;
        }
        int lastIdx = size() - 1;

        swap(0, lastIdx);
        E removed = heap.remove(lastIdx);
        siftDown(0);
        return removed;
    }

    public void offer(E e) {
        if (e == null) {
            throw new IllegalStateException();
        }
        heap.add(e);
        siftUp(size() - 1);
    }

    private void siftUp(int i) {
        int p = (i - 1) / degree;
        while (i > 0 && less(i, p)) {
            swap(i, p);
            i = p;
            p = (i - 1) / degree;
        }
    }

    private void swap(int i, int j) {
        if (i != j) {
            E tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
        }
    }

    private void siftDown(int i) {
        int suc = successor(i * degree + 1, i * degree + degree);
        while (suc < size() && less(suc, i)) {
            swap(i, suc);
            // move to next pair
            i = suc;
            suc = successor(i * degree + 1, i * degree + degree);
        }
    }

    private int successor(int from, int to) {
        int succ = from;
        for (int i = from + 1; i <= to && i < size(); i++) {
            if (less(i,succ)) {
                succ = i;
            }
        }
        return succ;
    }

    private boolean less(int i, int j) {
        E e1 = heap.get(i);
        E e2 = heap.get(j);
        return e1.compareTo(e2) < 0;
    }


    @Override
    public String toString() {
        return heap.toString();
    }

    private boolean isMinHeap(int pos) {
        if (pos >= size()) {
            return true;
        }

        int from = pos * degree + 1;
        int to =  pos * degree + degree;

        for (int i = from; i <= to && i < size(); i++) {
            if (less(i, pos)) {
                return false;
            }
        }
        boolean isMin = true;
        for (int i = from; i <= to && i < size(); i++) {
            isMin = isMin && isMinHeap(i);
        }
        return isMin;
    }

    public boolean isMinHeap() {
        return isMinHeap(0);
    }

    public static void main(String[] args) {

        final MinDHeap<String> pq = new MinDHeap<>(4);


        pq.offer("k");
        pq.offer("f");
        pq.offer("n");
        pq.offer("c");
        pq.offer("h");
        pq.offer("b");
        pq.offer("a");

        System.out.println(pq.isMinHeap());
        System.out.println(pq);

        final String polled = pq.poll();
        System.out.println(polled);
        System.out.println(pq);
    }

}
