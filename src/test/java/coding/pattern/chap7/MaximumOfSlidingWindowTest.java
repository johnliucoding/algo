package coding.pattern.chap7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MaximumOfSlidingWindowTest {

    @Test
    void maxSlidingWindow() {
        int[] ints = MaximumOfSlidingWindow.maxSlidingWindow(new int[]{3, 2, 4, 1, 2, 1, 1}, 4);
        assertArrayEquals(new int[]{4, 4, 4, 2}, ints);
    }
}