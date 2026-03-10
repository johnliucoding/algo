package coding.pattern.chap8;

import coding.pattern.chap3.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author liuxiaodong02
 */
public class MergeSortedLinkedList {

    public static <T extends Comparable<T>> ListNode<T> merge(List<ListNode<T>> lists,
                                                               Comparator<ListNode<T>> cmp) {

        var min_heap = new PriorityQueue<>(cmp);
        // push the head of each linked list into the heap
        for (var head : lists) min_heap.add(head);


        // a dummy node to point to the head of the output linked list
        ListNode<T> fake_head = new ListNode<>();

        // a pointer to iterate through the combined linked list as we add nodes to it
        ListNode<T> curr = fake_head;
        while (!min_heap.isEmpty()) {
            // poll min node
            var element = min_heap.poll();

            // min node's next element put to head
            var next = element.next;
            if(next != null) {
                min_heap.add(next);
            }

            // add to tracking pointer
            curr.next = element;
            curr = curr.next;
        }

        return fake_head.next;
    }
}
