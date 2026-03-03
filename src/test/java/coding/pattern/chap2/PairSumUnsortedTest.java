package coding.pattern.chap2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 * @date 2026/3/3 16:46
 */
class PairSumUnsortedTest {

    @Test
    void pairSumUnsortedTwoPassTest() {
        var inputs = new int[]{-1, 3, 4, 2};
        var target = 3;
        var expected = new int[]{0, 2};
        int[] result = PairSumUnsorted.twoPass(inputs, target);

        // the order of indexes in the result doesn't matter
        Arrays.sort(expected);
        Arrays.sort(result);

        assertArrayEquals(expected, result);
    }

    @Test
    void pairSumUnsortedOnePassTest() {
        var inputs = new int[]{-1, 3, 4, 2};
        var target = 3;
        var expected = new int[]{0, 2};

        int[] result = PairSumUnsorted.onePass(inputs, target);
        // the order of indexes in the result doesn't matter
        Arrays.sort(expected);
        Arrays.sort(result);

        assertArrayEquals(expected, result);
    }

}