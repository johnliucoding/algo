package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class KnapsackTest {

    @Test
    void maxValue() {
        int cap = 7;
        int[] weights = {5,3,4,1};
        int[] values = {70,50,40,10};
        int result = Knapsack.maxValue(cap, weights, values);
        assertEquals(90,result);

        int result2 = Knapsack.maxValueOptimized(cap, weights, values);
        assertEquals(90,result2);
    }
}