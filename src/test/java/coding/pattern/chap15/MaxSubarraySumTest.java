package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MaxSubarraySumTest {

    @Test
    void maxSubarraySum1() {
        int[] inputs = {3,1,-6,2,-1,4,-9};
        int result1 = MaxSubarraySum.maxSubarraySum1(inputs);
        int result2 = MaxSubarraySum.maxSubarraySum2(inputs);
        int result3 = MaxSubarraySum.maxSubarraySum3(inputs);
        assertEquals(5,result1);
        assertEquals(5,result2);
        assertEquals(5,result3);
    }
}