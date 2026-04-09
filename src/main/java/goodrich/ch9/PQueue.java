package ch9;


import java.util.*;

/**
 * @author Liu Xiaodong
 * @since 2024/9/1 1:12 AM
 */
public class PQueue<E> {
    // the number of elements currently inside the heap
    private int heapSize = 0;
    // the internal capacity of the heap
    private int heapCapacity = 0;
    // a dynamic list to track the elements inside the heap
    private List<E> heap = null;
    // map keeps track of the possible indices a particular
    // node value is found in the heap.
    // having this mapping lets us have O(log(n)) removals
    // and O(1) element containment check at the cost of
    // some additional space and minor overhead
    private Map<E, TreeSet<Integer>> map = new HashMap<>();

    private Comparator<E> comparator = null;

    // construct a priority queue with an initial capacity
    public PQueue(int size, Comparator<E> comp) {
        heapCapacity = size;
        heap = new ArrayList<>(size);
        comparator = comp;
    }

    // construct an empty priority
    public PQueue(Comparator<E> comp) {
        this(1, comp);
    }

    public PQueue(E[] elems, Comparator<E> comp) {
        comparator = comp;
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<>(heapCapacity);

        // place all element in heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }

        // heapify process, O(n)
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    public PQueue(Collection<? extends E> elems, Comparator<E> comp) {
        this(elems.size(), comp);
        for (E elem : elems) {
            add(elem);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public void clear() {
        for (int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    public E peak() {
        if (isEmpty()) {
            return null;
        }
        return heap.getFirst();
    }

    public E poll() {
        return removeAt(0);
    }

    public boolean contains(E elem) {
        if (elem == null) {
            return false;
        }
        return map.containsKey(elem);
        // linear scan
//        for (int i = 0; i < heapSize; i++) {
//            if(heap.get(i).equals(elem)) {
//                return true;
//            }
//        }
//        return false;
    }


    public void add(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException();
        }
        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        } else {
            heap.add(elem);
            heapCapacity++;
        }
        mapAdd(elem, heapSize);
        swim(heapSize);
        heapSize++;
    }

    private boolean less(int i, int j) {
        final E node1 = heap.get(i);
        final E node2 = heap.get(j);
        return comparator.compare(node1, node2) <= 0;
    }

    private void swim(int k) {
        int parent = (k - 1) / 2;

        while (k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;
            parent = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;
            if (right < heapSize && less(right, left)) {
                smallest = right;
            }
            if (left >= heapSize || less(k, smallest)) {
                break;
            }
            swap(smallest, k);
            k = smallest;
        }
    }

    private void swap(int i, int j) {
        final E node1 = heap.get(i);
        final E node2 = heap.get(j);
        heap.set(i, node2);
        heap.set(j, node1);

        mapSwap(node1, node2, i, j);
    }

    public boolean remove(E elem) {
        if (elem == null) {
            return false;
        }
        // linear removal via search
//        for (int i = 0; i < heapSize; i++) {
//            if(elem.equals(heap.get(i))) {
//                removeAt(i);
//                return true;
//            }
//        }
//        return false;

        Integer index = mapGet(elem);
        if (index != null) {
            removeAt(index);
        }
        return index != null;
    }

    private E removeAt(int i) {
        if (isEmpty()) {
            return null;
        }
        heapSize--;
        final E removed_data = heap.get(i);
        swap(i, heapSize);

        heap.set(heapSize, null);
        mapRemove(removed_data, heapSize);

        if (i == heapSize) return removed_data;

        final E elem = heap.get(i);
        sink(i);
        if (heap.get(i).equals(elem)) {
            swim(i);
        }
        return removed_data;
    }

    public boolean isMinHeap(int k) {
        if (k >= heapSize) return true;
        int left = 2 * k + 1;
        int right = 2 * k + 2;
        if (left < heapSize && !less(k, left)) {
            return false;
        }
        if (right < heapSize && !less(k, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    private void mapAdd(E elem, int index) {
        TreeSet<Integer> set = map.get(elem);
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(elem, set);
        } else {
            set.add(index);
        }
    }

    private void mapRemove(E elem, int index) {
        final TreeSet<Integer> set = map.get(elem);
        set.remove(index);
        if (set.isEmpty()) {
            map.remove(elem);
        }
    }

    private Integer mapGet(E elem) {
        final TreeSet<Integer> set = map.get(elem);
        if (set != null) {
            return set.last();
        }
        return null;
    }

    private void mapSwap(E node1, E node2, int node1Index, int node2Index) {
        final TreeSet<Integer> set1 = map.get(node1);
        final TreeSet<Integer> set2 = map.get(node2);
        set1.remove(node1Index);
        set2.remove(node2Index);
        set1.add(node2Index);
        set2.add(node1Index);
    }
}
