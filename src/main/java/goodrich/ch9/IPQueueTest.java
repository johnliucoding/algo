package goodrich.ch9;

/**
 * @author Liu Xiaodong
 * @since 2024/9/2 11:08 PM
 */
public class IPQueueTest {
    public static void main(String[] args) {
        final IPQueue<String> ipQueue = new IPQueue<>(12, String::compareTo);

        ipQueue.insert(1, "k");
        ipQueue.insert(3, "f");
        ipQueue.insert(4, "n");
        ipQueue.insert(6, "c");
        ipQueue.insert(8, "h");
        ipQueue.insert(10, "b");

        System.out.println(ipQueue.isMinHeap());
        System.out.println(ipQueue.toString());

        final MinIndexedDHeap<String> dHeap = new MinIndexedDHeap<>(2, 12);
        dHeap.insert(1, "k");
        dHeap.insert(3, "f");
        dHeap.insert(4, "n");
        dHeap.insert(6, "c");
        dHeap.insert(8, "h");
        dHeap.insert(10, "b");

        System.out.println(dHeap.isMinHeap());
        System.out.println(dHeap.toString());
    }
}
