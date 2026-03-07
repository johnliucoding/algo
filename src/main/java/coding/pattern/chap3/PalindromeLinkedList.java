package coding.pattern.chap3;

public class PalindromeLinkedList {

    public static <T> boolean test(ListNode<T> head) {

        // find the middle of the linked list and then reverse the second half of
        // the linked list starting at this midpoint
        ListNode<T> mid = find_mid(head);
        ListNode<T> new_head = reverse(mid);

        var ptr_left = head;
        var ptr_right = new_head;

        // compare the first half and the reversed second half of the linked list
        while (ptr_right != null) {
            if(!ptr_left.data.equals(ptr_right.data)) {
                return false;
            }
            ptr_left = ptr_left.next;
            ptr_right = ptr_right.next;
        }
        return true;
    }

    private static <T> ListNode<T> reverse(ListNode<T> head) {
        ListNode<T> pre = null;
        var cur = head;
        while (cur.next != null) {
            var next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return cur;
    }

    private static <T> ListNode<T> find_mid(ListNode<T> head) {
        var slow = head;
        var fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
