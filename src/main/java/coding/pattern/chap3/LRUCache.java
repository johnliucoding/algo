package coding.pattern.chap3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxiaodong02
 */
public class LRUCache {
    private static class DoublyLinkedListNode {
        int key;
        int value;
        DoublyLinkedListNode next;
        DoublyLinkedListNode prev;
        public DoublyLinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;

    // dummy node to avoid null processing
    private final DoublyLinkedListNode head = new DoublyLinkedListNode(Integer.MIN_VALUE, -1);
    private final DoublyLinkedListNode tail = new DoublyLinkedListNode(Integer.MAX_VALUE, -1);

    private final Map<Integer, DoublyLinkedListNode> hashMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // key-value pairs are ordered from the least recently used(left) to the most recently used(right)

    // add

    // adding to the most-recently-used end of the list
    // if capacity is full, before adding, evicting the least recently used

    // 2 operation: 1 add at right 2 remove at left

    // get

    // search a key-value, moving to right


    private void add_to_tail(DoublyLinkedListNode node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void remove_node(DoublyLinkedListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }


    @Override
    public String toString() {
        DoublyLinkedListNode node = head.next;
        StringBuilder sb = new StringBuilder();
        sb.append(hashMap.size()).append("/").append(capacity);
        sb.append("[ ");
        while (node != tail) {
            sb.append(node.key).append(":").append(node.value).append(" ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public int get(int key) {
        if(!hashMap.containsKey(key)) {
            return -1;
        }
        DoublyLinkedListNode node = hashMap.get(key);
        remove_node(node);
        add_to_tail(node);
        return node.value;
    }
    public void put(int key, int value) {
        // if a node with this key already exists, remove it from the linked list
        if(hashMap.containsKey(key)) {
            remove_node(hashMap.get(key));
        }
        DoublyLinkedListNode node = new DoublyLinkedListNode(key, value);
        hashMap.put(key, node);
        // remove the least recently used node from the cache if adding
        // this new node will result in an overflow
        if(hashMap.size() > capacity) {
            hashMap.remove(head.next.key);
            remove_node(head.next);
        }
        add_to_tail(node);
    }
}
