package coding.pattern.chap10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class FindSubarrayTest {

    @Test
    void subarraySumToK() {
        int[] nums = new int[] {1, 2, -1, 1, 2};
        int k = 3;
        int result = FindSubarray.subarraySumToK(nums, k);
        assertEquals(3, result);
    }

    @Test
    void subarraySumToK2() {
        int[] nums = new int[] {1, 2, -1, 1, 2};
        int k = 3;
        int result = FindSubarray.subarraySumToK2(nums, k);
        assertEquals(3, result);
    }
}