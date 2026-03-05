package coding.pattern.chap3;

public class Reversal {

    // time O(n)
    // space O(1)
    public static <T> ListNode<T> reverse(ListNode<T> list) {
        ListNode<T> pre = null;
        ListNode<T> cur = list;
        // reverse the direction of each node's pointer until 'cur' node is null
        while (cur != null) {
            ListNode<T> next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 'pre' node will be pointing at the head of the reversed linked list
        return pre;
    }
    // time O(n)
    // space O(n)
    public static <T> ListNode<T> reverseRecursive(ListNode<T> head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode<T> new_head = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return  new_head;
    }
}
