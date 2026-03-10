package coding.pattern.chap8;

import coding.pattern.chap3.ListNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MergeSortedLinkedListTest {

    private static final Logger log = LoggerFactory.getLogger(MergeSortedLinkedListTest.class);

    @Test
    void merge() {

        ListNode<Integer> l1 = buildList(1, 6);
        log.atInfo().setMessage(printList(l1)).log();
        ListNode<Integer> l2 = buildList(1, 4, 6);
        log.atInfo().setMessage(printList(l2)).log();
        ListNode<Integer> l3 = buildList(3, 7);
        log.atInfo().setMessage(printList(l3)).log();

        ListNode<Integer> merge = MergeSortedLinkedList.merge(List.of(l1, l2, l3), Comparator.comparing(node -> node.data));
        log.atInfo().setMessage(printList(merge)).log();
    }

    ListNode<Integer> buildList(int... values) {
        var fake_head = new ListNode<Integer>();

        var curr = fake_head;
        for (int value : values) {
            var node = new ListNode<Integer>();
            node.data = value;

            curr.next = node;
            curr = curr.next;
        }

        return fake_head.next;

    }

    String printList(ListNode<Integer> head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.data).append(" > ");
            head = head.next;
        }
        return sb.toString();
    }
}