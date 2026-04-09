package ch9.heap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Liu Xiaodong
 * @since 2025/2/28 11:25 AM
 */
public class MinIndexedHeap<E, K extends Comparable<K>> {

    private final HashMap<E, K> keyMap;
    private final HashMap<E, Integer> posMap;
    private final ArrayList<E> heap;

    public MinIndexedHeap() {
        this.keyMap = new HashMap<>();
        this.posMap = new HashMap<>();
        this.heap = new ArrayList<>();
    }

    public void insert(E elem, K key) {
        if (elem == null) {
            throw new IllegalArgumentException("element is null");
        }
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (contains(elem)) {
            throw new IllegalArgumentException("element already exists");
        }
        keyMap.put(elem, key);
        heap.add(elem);
        posMap.put(elem, heap.size() - 1);
        siftUp(heap.size() - 1);


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
            E ei = heap.get(i);
            Integer posi = posMap.get(ei);
            E ej = heap.get(j);
            Integer posj = posMap.get(ej);


            heap.set(i, ej);
            heap.set(j, ei);
            posMap.put(ei, posj);
            posMap.put(ej, posi);
        }
    }

    private boolean less(int i, int j) {
        final K ki = keyMap.get(heap.get(i));
        final K kj = keyMap.get(heap.get(j));
        return less(ki, kj);
    }

    public int size() {
        return heap.size();
    }

    public boolean contains(E elem) {
        return keyMap.containsKey(elem);
    }

    public record ElementKey<E, K>(E elem, K key) {
    }

    public ElementKey<E, K> poll() {
        if (size() == 0) {
            return null;
        }
        int lastIdx = size() - 1;
        swap(0, lastIdx);

        E elem = heap.remove(lastIdx);
        K key = keyMap.remove(elem);
        posMap.remove(elem);


        siftDown(0);

        return new ElementKey<>(elem, key);
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

    public void decreaseKey(E elem, K key) {
        if (elem == null) {
            throw new IllegalArgumentException("element is null");
        }
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (!contains(elem)) {
            throw new IllegalArgumentException("element doesn't exists");
        }

        if (less(key, keyMap.get(elem))) {
            keyMap.put(elem, key);
            siftUp(posMap.get(elem));
        }
    }

    private boolean less(K key1, K key2) {
        return key1.compareTo(key2) < 0;
    }

    public void increaseKey(E elem, K key) {
        if (elem == null) {
            throw new IllegalArgumentException("element is null");
        }
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (!contains(elem)) {
            throw new IllegalArgumentException("element doesn't exists");
        }

        if (less(keyMap.get(elem), key)) {
            keyMap.put(elem, key);
            siftDown(posMap.get(elem));
        }
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

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < heap.size(); i++) {
            final E e = heap.get(i);
            final K k = keyMap.get(e);
            sb.append("{").append(e).append(",").append(k).append("}");
            if (i != heap.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        final MinIndexedHeap<String, Integer> ipq = new MinIndexedHeap<>();
        ipq.insert("a", 3);
        ipq.insert("b", 2);
        ipq.insert("c", 1);

        System.out.println(ipq.isMinHeap());
        System.out.println(ipq);

        final ElementKey<String, Integer> poll = ipq.poll();
        System.out.println(poll);
        System.out.println(ipq);

        ipq.decreaseKey("a", 1);

        System.out.println(ipq);
    }

}
