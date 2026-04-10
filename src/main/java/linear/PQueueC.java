package linear;

import java.util.*;
import java.util.List;

public class PQueueC<T extends Comparable<T>> {

    // the number of elements
    private int size = 0;
    // internal capacity of the heap
    private int capacity = 0;
    // a dynamic array to track the elements inside the heap
    private List<T> heap = null;
    // a map keeps track of the possible indices a particular element
    // is found in the heap. having this mapping lets us have O(log(n)) removals and O(1)
    // element containment check at the cost of some additional space and overhead
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // construct and initially one pq
    public PQueueC() {
        this(1);
    }
    // construct a pq with an initial capacity
    public PQueueC(int sz) {
        heap = new ArrayList<>(sz);
    }
    // construct a pq using heapify in O(n) time
    public PQueueC(T[] elems) {
        size = capacity = elems.length;
        heap = new ArrayList<>(capacity);

        // place all element in heap
        for (int i = 0; i < size; i++) {
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }

        // heapify
        for (int i = Math.max(0, (size/2)-1); i >=0; i--) {
            sink(i);
        }
    }
    // pq construction O(nlog(n))
    public PQueueC(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) {
            offer(elem);
        }
    }

    // O(1)
    public boolean isEmpty() {
        return size == 0;
    }
    // O(n)
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            heap.set(i, null);
        }
        size = 0;
        map.clear();
    }
    // O(1)
    public int size() {
        return size;
    }

    // returns the value of the element with the lowest priority in this pq.
    // is pq is empty null is returned
    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    // remove the root of the heap, O(log(n))
    public T poll() {
        return removeAt(0);
    }

    // add an element to the pq, the element must not be null
    // O(log(n))
    public void offer(T elem) {
        if(elem == null) throw new IllegalArgumentException();

        if(size < capacity) {
            heap.set(size, elem);
        } else {
            heap.add(elem);
            capacity++;
        }
        mapAdd(elem, size);
        swim(size);
        size++;
    }

    // test if an element is in heap. O(1)
    public boolean contains(T elem) {
        // map lookup to check containment, O(1)
        if(elem == null) return false;
        return map.containsKey(elem);

        // linear search to check containment, O(n)
//        for (int i = 0; i < size; i++) {
//            if(heap.get(i).equals(elem)) {
//                return true;
//            }
//        }
//        return false;
    }

    // remove a particular element in the heap O(log(n))
    public boolean remove(T elem) {
        if(elem == null) return false;
        // linear removal via search, O(n)
//        for (int i = 0; i < size; i++) {
//            if(elem.equals(heap.get(i))) {
//                removeAt(i);
//                return true;
//            }
//        }
        
        // removal with map O(log(n))
        Integer index = mapGet(elem);
        if(index != null) {
            removeAt(index);
        }
        return index != null;
        
    }
    // recursively checks if this heap is a min heap
    // for testing purposes to make sure the heap invariant is still being maintained
    // called this method with k=0 to start at the root
    public boolean isMinHeap(int k) {
        // if outside the bounds of the heap return true
        if(k >= size) return true;
        int left = 2*k + 1; // left node
        int right = 2*k +2; // right node

        // make sure that the current node k is less than
        // both of its children left, an right if they exist
        // return false otherwise
        if(left < size && !less(k, left)) return false;
        if(right < size && !less(k, right)) return false;

        return isMinHeap(left) && isMinHeap(right);

    }
    public String toString() {
        return heap.toString();
    }



    // remove a element at particular index, O(log(n))
    private T removeAt(int i) {
        if(isEmpty()) return null;
        size--;
        final T removed = heap.get(i);
        swap(i, size);

        // obliterate the value
        heap.set(size, null);
        mapRemove(removed, size);

        // if removed last element
        if(i == size) return removed;

        final T elem = heap.get(i);
        // try sinking element
        sink(i);
        // if sinking did not work try swimming
        if(heap.get(i).equals(elem))  {
            swim(i);
        }
        return removed;
    }

    // test if the element i <= j
    // assumes i, j are valid indices O(1)
    private boolean less(int i, int j) {
        final T elem1 = heap.get(i);
        final T elem2 = heap.get(j);
        return elem1.compareTo(elem2) <= 0;

    }

    // swap two nodes, assumes i & j are valid O(1)
    private void swap(int i, int j) {
        final T elemi = heap.get(i);
        final T elemj = heap.get(j);
        heap.set(i, elemj);
        heap.set(j, elemi);

        mapSwap(elemi, elemj, i, j);

    }

    // top down node sink, O(log(n))
    private void sink(int k) {
        while (true) {
            int left = 2*k + 1; // left node
            int right = 2*k +2; // right node
            int smallest = left; // assume left is the smallest node of the two children.

            // find which is smaller left or right
            // if right is smaller set smallest to the right
            if(right < size && less(right, left)) {
                smallest = right;
            }

            // stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymore
            if(left>= size || less(k, smallest)) {
                break;
            }
            // move down the tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    // bottom up node swim O(log(n))
    private void swim(int k) {
        // grab the index of the next parent node
        int parent = (k-1)/2;
        // keep swimming while we have not reached the root
        // and while we're less than our parent
        while (k > 0 && less(k, parent)) {
            // exchange k with parent
            swap(parent, k);
            k = parent;
            // grab the index of the next parent node
            parent = (k-1)/2;
        }
    }
    // extract an index for the given element
    // NOTE: if a value exists multiple times in the heap the
    // highest index is returned (this has arbitrarily been chosen)
    private Integer mapGet(T elem) {
        final TreeSet<Integer> set = map.get(elem);
        if(set!=null) return set.last();
        return null;
    }

    // removes the index at a given value, O(log(n))
    private void mapRemove(T elem, int i) {
        final TreeSet<Integer> set = map.get(elem);
        set.remove(i); // TreeSet take O(log(n)) removal time
        if(set.size() == 0) {
            map.remove(elem);
        }
    }

    private void mapAdd(T elem, int i) {
        TreeSet<Integer> set = map.get(elem);

        // new value being inserted in map
        if(set == null) {
            set = new TreeSet<>();
            set.add(i);
            map.put(elem, set);
        } else {
            // elem already exists in map
            set.add(i);
        }
    }
    // exchange the index of two elements internally within the map
    private void mapSwap(T elemi, T elemj, int i, int j) {
        final TreeSet<Integer> seti = map.get(elemi);
        final TreeSet<Integer> setj = map.get(elemj);

        seti.remove(i);
        setj.remove(j);

        seti.add(j);
        setj.add(i);

    }

    public static void main(String[] args) {
        final PQueueC<Integer> pq = new PQueueC<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(6);
        pq.offer(2);
        pq.offer(7);
        pq.offer(3);
        pq.offer(8);
        pq.offer(4);
        pq.offer(9);
        pq.offer(10);
        pq.offer(49);

        System.out.println(pq);

        System.out.println(pq.size());
        System.out.println(pq.capacity);

        System.out.println("poll numbers: ");
        while (!pq.isEmpty()) {
            int num = pq.poll();
            System.out.println(num);

        }

    }
}
