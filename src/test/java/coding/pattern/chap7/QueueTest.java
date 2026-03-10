package coding.pattern.chap7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class QueueTest {

    @Test
    void queueTest() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        int a = queue.dequeue();
        queue.enqueue(3);
        int b = queue.peek();
        assertEquals(1,a);
        assertEquals(2,b);
        assertEquals(2,queue.peek());
    }

}