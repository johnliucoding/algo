package coding.pattern.chap9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class IdentifyAllIntervalOverlapTest {

    @Test
    void overlaps() {
        var intervals1 = Arrays.asList(new Interval(1,4), new Interval(5,6), new Interval(9,10));
        var intervals2 = Arrays.asList(new Interval(2,7), new Interval(8,9));
        List<Interval> overlaps = IdentifyAllIntervalOverlap.overlaps(intervals1, intervals2);
        var expected = Arrays.asList(new Interval(2,4), new Interval(5,6), new Interval(9,9));
        assertEquals(expected, overlaps);
    }
}