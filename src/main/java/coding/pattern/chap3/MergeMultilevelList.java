package coding.pattern.chap3;

import java.util.Objects;

public class MergeMultilevelList {

    public static <T> void merge(MultilevelListNode<T> head) {
        Objects.requireNonNull(head, "head must not be null");

        // find the tail of the linked list at the first level.
        var tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        // process each node at the current level.
        // If a node has a child linked list, append it the tail and then update the tail the end of the extended linked list.
        // Continue until all nodes at the current level are processed.
        var cur = head;
        while (cur != null) {
            if(cur.child != null) {
                tail.next = cur.child;
                while (tail.next!=null) {
                    tail = tail.next;
                }
                // disconnect the child linked list from the current node
                cur.child = null;
            }
            cur = cur.next;
        }
    }
}
