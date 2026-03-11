package coding.pattern.chap10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class ProductArrayWithoutCurrentTest {

    @Test
    void productArray() {
        int[] ints = ProductArrayWithoutCurrent.productArray(new int[]{2, 3, 1, 4, 5});
        assertArrayEquals(new int[]{60, 40, 120, 30, 24}, ints);
    }
}