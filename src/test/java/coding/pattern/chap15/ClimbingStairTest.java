package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class ClimbingStairTest {

    @Test
    void climbStairsTopDown() {

        int result = new ClimbingStair().climbStairsTopDown(4);
        assertEquals(5, result);


        assertEquals(5, new ClimbingStair().climbStairsBottomUp(4));

        assertEquals(5, new ClimbingStair().climbStairsBottomUpOptimized(4));
    }
}