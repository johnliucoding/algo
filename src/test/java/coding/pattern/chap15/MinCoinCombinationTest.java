package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MinCoinCombinationTest {

    @Test
    void assertSuccess() {
        int result1 = MinCoinCombination.minCoinCombinationTopDown(new int[]{1, 2, 3}, 5);
        assertEquals(2, result1);
        int result2 = MinCoinCombination.minCoinCombinationBottomUp(new int[]{1, 2, 3}, 5);
        assertEquals(2, result2);
    }

    @Test
    void assertFailure() {
        int result1 = MinCoinCombination.minCoinCombinationTopDown(new int[]{2, 4}, 5);
        assertEquals(-1, result1);
        int result2 = MinCoinCombination.minCoinCombinationBottomUp(new int[]{2, 4}, 5);
        assertEquals(-1, result2);
    }
}