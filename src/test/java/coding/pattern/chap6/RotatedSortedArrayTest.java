package coding.pattern.chap6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RotatedSortedArrayTest {

    @Test
    void findTarget() {
        int result = RotatedSortedArray.findTarget(new int[]{8, 9, 1, 2, 3, 4, 5, 6, 7}, 1);
        assertEquals(2, result);
    }
}