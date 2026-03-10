package coding.pattern.chap8;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Median {


    // max-heap
    private final PriorityQueue<Integer> left_hp;
    // min-heap
    private final PriorityQueue<Integer> right_hp;

    public Median() {
        left_hp = new PriorityQueue<>(Comparator.<Integer>naturalOrder().reversed()); // need this to make generic working
        right_hp = new PriorityQueue<>(Comparator.naturalOrder());
    }

    public void add(int num) {
        // if the left half is empty or 'num' is less than or equal to the max of 'left_half'
        // it belongs to the left half
        if(left_hp.isEmpty() || num <= left_hp.peek()) {
            left_hp.add(num);
            // rebalancing the heaps
            if(left_hp.size() > right_hp.size() + 1) {
                right_hp.add(left_hp.poll());
            }
        } else {
            right_hp.add(num);
            if(right_hp.size() > left_hp.size()) {
                left_hp.add(right_hp.poll());
            }
        }


    }
    public double get_median() {
        if(left_hp.size() > right_hp.size()) {
            return left_hp.peek();
        } else {
            return (left_hp.peek() + right_hp.peek()) / 2.0;
        }
    }
}
