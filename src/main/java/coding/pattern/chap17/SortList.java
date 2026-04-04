package coding.pattern.chap17;

import coding.pattern.chap3.ListNode;


public class SortList {

    public static <T extends Comparable<T>> ListNode<T> sort(ListNode<T> head){
        if(head == null ||  head.next == null) return head;
        // split the linked list into halves using the fast and slow pointer technique
        ListNode<T> second_head = split_list(head);
        // recursively sort both halves
        ListNode<T> first_half_sorted = sort(head);
        ListNode<T> second_half_sorted = sort(second_head);
        // merge the sorted sublists
        return merge(first_half_sorted, second_half_sorted);

    }

    private static <T extends Comparable<T>> ListNode<T> merge(ListNode<T> head1, ListNode<T> head2) {
        ListNode<T> dummy = new ListNode<>();
        // this pointer will be used to append nodes to the tail of the merged linked list
        ListNode<T> tail = dummy;
        // continually append the node with the smaller value from each linked list to the merged linked list
        // until one of the linked lists has no more nodes to merge
        while(head1 != null && head2 != null){
            if(head1.data.compareTo(head2.data) <= 0){
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        // one of the two linked lists could still have nodes remaining
        // attach those nodes to the end of the merged linked list
        tail.next = head1 == null ? head2 : head1;
        return dummy.next;
    }

    private static <T extends Comparable<T>> ListNode<T> split_list(ListNode<T> head) {
        ListNode<T> slow = head;
        ListNode<T> fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode<T> second_head = slow.next;
        slow.next = null;
        return second_head;
    }
}
