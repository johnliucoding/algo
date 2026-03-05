package coding.pattern.chap2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class ChainOfConsecutiveNumbersTest {

    @Test
    void test() {
        int[] nums = {1, 6, 2, 5, 8, 7, 10, 3};
        int expect = 4;

        int result1 = ChainOfConsecutiveNumbers.bruteForce(nums);
        int result2 = ChainOfConsecutiveNumbers.longestChainSize(nums);
        assertEquals(expect, result1);
        assertEquals(expect, result2);
    }

}