package coding.pattern.chap10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class SumBetweenRangeTest {

    @Test
    void sumBetween() {

        SumBetweenRange sumBetweenRange = new SumBetweenRange(new int[]{3, -7, 6, 0, -2, 5});

        assertEquals(2, sumBetweenRange.sumBetween(0, 3));
        assertEquals(4, sumBetweenRange.sumBetween(2, 4));
        assertEquals(6, sumBetweenRange.sumBetween(2, 2));

    }
}