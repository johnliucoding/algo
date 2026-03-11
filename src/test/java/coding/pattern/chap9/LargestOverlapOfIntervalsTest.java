package coding.pattern.chap9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class LargestOverlapOfIntervalsTest {

    @Test
    void largestOverlap() {

        var inputs = Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(4, 8), new Interval(6, 7), new Interval(5, 7));
        int result = LargestOverlapOfIntervals.largestOverlap(inputs);
        assertEquals(3, result);
    }
}