package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class NeighborhoodBurglaryTest {

    @Test
    void maxMoney() {

        int result = NeighborhoodBurglary.maxMoney(new int[]{200, 300, 200, 50});
        assertEquals(400, result);

        int result2 = NeighborhoodBurglary.maxMoneyOptimized(new int[]{200, 300, 200, 50});
        assertEquals(400, result2);
    }
}