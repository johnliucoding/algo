package coding.pattern.chap4;

import coding.pattern.chap3.ListNode;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Objects;

public class CycleDetection {



    public static <T> boolean useSet(ListNode<T> head) {
        HashSet<Integer> set = new HashSet<>();
        var cur = head;
        while (cur != null) {
            // cycle detected if the current node has already been visited
            if (set.contains(System.identityHashCode(cur))) {
                return true;
            }
            set.add(System.identityHashCode(cur));
            cur = cur.next;
        }
        return false;
    }

    public static <T> boolean useTwoPointers(ListNode<T> head) {
        Objects.requireNonNull(head);

       var slow = head;
       var fast = head;
       while (fast != null && fast.next != null) {
           slow = slow.next;
           fast = fast.next.next;
           if(fast == slow) {
               return true;
           }
       }

       return false;

    }
}
