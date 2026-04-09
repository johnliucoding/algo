package goodrich.ch9.heap;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * @author Liu Xiaodong
 * @since 2025/2/28 10:28 AM
 */
public class MinHeap<E> {

    private final ArrayList<E> heap;
    private final Comparator<E> comparator;

    public MinHeap(Comparator<E> cmp) {
        this.heap = new ArrayList<>();
        this.comparator = cmp;
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
        E ans = heap.get(0);
        swap(0, lastIdx);
        heap.remove(lastIdx);
        siftDown(0);
        return ans;
    }

    public void offer(E e) {
        if (e == null) {
            throw new IllegalStateException();
        }
        heap.add(e);
        siftUp(size() - 1);
    }

    private void siftUp(int i) {
        int p = (i - 1) / 2;
        while (i > 0 && less(i, p)) {
            swap(i, p);
            i = p;
            p = (i - 1) / 2;
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
        int suc = successor(i);
        while (suc < size() && less(suc, i)) {
            swap(i, suc);
            // move to next pair
            i = suc;
            suc = successor(i);
        }
    }

    private int successor(int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int smallest = left;
        if (right < size() && less(right, left)) {
            smallest = right;
        }
        return smallest;
    }

    private boolean less(int i, int j) {
        E e1 = heap.get(i);
        E e2 = heap.get(j);
        return comparator.compare(e1, e2) < 0;
    }


    @Override
    public String toString() {
        return heap.toString();
    }

    private boolean isMinHeap(int pos) {
        if (pos >= size()) {
            return true;
        }
        int left = 2 * pos + 1;
        int right = 2 * pos + 2;
        if (left < size() && !less(pos, left)) {
            return false;
        }
        if (right < size() && !less(pos, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }

    public boolean isMinHeap() {
        return isMinHeap(0);
    }

    public static void main(String[] args) {

        final MinHeap<String> pq = new MinHeap<>(String::compareTo);


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
