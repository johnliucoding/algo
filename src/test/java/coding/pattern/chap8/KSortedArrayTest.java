package coding.pattern.chap8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KSortedArrayTest {

    @Test
    void sort() {

        int[] nums = {5, 1, 9, 4, 7, 10};
        int k = 2;

        int[] result = KSortedArray.sort(nums, k);
        assertArrayEquals(new int[]{1, 4, 5, 7, 9, 10}, result);
    }
}