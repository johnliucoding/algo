package coding.pattern.chap4;

import coding.pattern.chap3.ListNode;

import java.util.Objects;


public class LinkedListMidpoint {

    // second when even
    public static <T> ListNode<T> mid(ListNode<T> head) {
        Objects.requireNonNull(head);
        var slow = head;
        var fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static <T> ListNode<T> mid_lef(ListNode<T> head) {
        Objects.requireNonNull(head);
        var slow = head;
        var fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
