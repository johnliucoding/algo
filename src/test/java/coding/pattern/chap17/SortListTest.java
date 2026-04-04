package coding.pattern.chap17;

import coding.pattern.chap3.ListNode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class SortListTest {

    private static final Logger log = LoggerFactory.getLogger(SortListTest.class);

    @Test
    void sort() {
        ListNode<Integer> input = buildList();
        log.atInfo().setMessage("input: {}").addArgument(listToString(input)).log();

        ListNode<Integer> result = SortList.sort(input);
        log.atInfo().setMessage("result: {}").addArgument(listToString(result)).log();
    }

    ListNode<Integer> buildList(){
        ListNode<Integer> a = new ListNode<>();
        a.data = 1;
        ListNode<Integer> b = new ListNode<>();
        b.data = 5;
        ListNode<Integer> c = new ListNode<>();
        c.data = 4;
        ListNode<Integer> d = new ListNode<>();
        d.data = 2;
        ListNode<Integer> e = new ListNode<>();
        e.data = 3;
        e.next = d;
        d.next = c;
        c.next = b;
        b.next = a;
        return e;
    }

    String listToString(ListNode<Integer> list){
        StringBuilder sb = new StringBuilder();
        while (list != null){
            sb.append(list.data);
            if(list.next != null){
                sb.append(" -> ");
            }
            list = list.next;
        }
        return sb.toString();
    }
}