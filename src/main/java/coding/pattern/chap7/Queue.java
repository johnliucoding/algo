package coding.pattern.chap7;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liuxiaodong02
 */
public class Queue {

    private final Deque<Integer> enqueue_stack = new ArrayDeque<>();
    private final Deque<Integer> dequeue_stack = new ArrayDeque<>();

    // O(1)
    public void enqueue(int value) {
        enqueue_stack.push(value);
    }

    // amortized O(1)
    public int dequeue() {
        transfer();
        if(dequeue_stack.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return dequeue_stack.pop();

    }


    private void transfer() {
        if(dequeue_stack.isEmpty()) {
            while (!enqueue_stack.isEmpty()) {
                dequeue_stack.push(enqueue_stack.pop());
            }
        }
    }
    public int peek() {
        transfer();
        if(dequeue_stack.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return dequeue_stack.peek();
    }

    public boolean isEmpty() {
        return dequeue_stack.isEmpty() && enqueue_stack.isEmpty();
    }
}
