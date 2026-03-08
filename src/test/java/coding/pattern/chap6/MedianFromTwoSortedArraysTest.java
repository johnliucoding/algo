package coding.pattern.chap6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianFromTwoSortedArraysTest {

    @Test
    void median() {

        double median1 = MedianFromTwoSortedArrays.median(new int[]{0, 2, 5, 6, 8}, new int[]{1, 3, 7});
        assertEquals(4.0, median1);

        double median2 = MedianFromTwoSortedArrays.median(new int[]{0, 2, 5, 6, 8}, new int[]{1, 3, 7, 9});
        assertEquals(5.0, median2);
    }
}