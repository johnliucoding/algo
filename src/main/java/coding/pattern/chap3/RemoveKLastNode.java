package coding.pattern.chap3;

public class RemoveKLastNode {

    public static <T> ListNode<T> remove(ListNode<T> head, int k) {

        // a dummy node to ensure there's a node before head
        // in case we need to remove the head node
        ListNode<T> dummy = new ListNode<>();
        dummy.next = head;

        var follower = dummy;
        var leader = dummy;

        // advance leader k steps ahead
        for (int i = 0; i < k; i++) {
            leader = leader.next;

            // if k is larger than the length of the linked list,
            // no node needs to be removed
            if(leader == null) {
                return head;
            }
        }
        // move leader to the end of the linked list
        // keeping follower k nodes behind
        while (leader.next != null) {
            leader= leader.next;
            follower =follower.next;
        }

        // remove the kth node from the end.
        ListNode<T> follower_next = follower.next;
        follower.next = follower.next.next;
        follower_next.next = null;
        return dummy.next;
    }
}
