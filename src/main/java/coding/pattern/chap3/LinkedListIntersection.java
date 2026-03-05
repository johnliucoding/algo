package coding.pattern.chap3;

public class LinkedListIntersection {

    public static <T> ListNode<T> intersect(ListNode<T> head_a, ListNode<T> head_b) {
        var pointer_a = head_a;
        var pointer_b = head_b;

        // traverse through list a with ptr-a and list b with ptr-b until they meet
        while (pointer_a != pointer_b) {

            // traverse list_a -> list_b by first traversing 'ptr_a'
            // and then, upon reaching the end of list_a, continue the
            // traversal from the head of list_b
            pointer_a = pointer_a == null ? head_b : pointer_a.next;

            // simultaneously, traverse list_b -> list_a
            pointer_b = pointer_b == null ? head_a : pointer_b.next;
        }
        // at this point, ptr_a and ptr_b either point to the intersection
        // node or both are null if the lists do not intersect, return either pointer
        return pointer_a;
    }
}
