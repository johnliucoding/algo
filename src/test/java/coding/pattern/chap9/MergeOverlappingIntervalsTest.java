package coding.pattern.chap9;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MergeOverlappingIntervalsTest {

    @Test
    void merge() {

        var inputs = List.of(new Interval(3, 4), new Interval(7, 8), new Interval(2, 5), new Interval(6, 7), new Interval(1, 4));
        var outputs = List.of(new Interval(1, 5), new Interval(6, 8));

        List<Interval> merge = MergeOverlappingIntervals.merge(inputs);
        assertEquals(merge, outputs);
    }

    @Test
    void mergeMutable() {


        var inputs = Arrays.asList(new Interval(3, 4), new Interval(7, 8), new Interval(2, 5), new Interval(6, 7), new Interval(1, 4));
        var outputs = Arrays.asList(new Interval(1, 5), new Interval(6, 8));

        List<Interval> merge = MergeOverlappingIntervals.mergeMutable(inputs);
        assertEquals(merge, outputs);
    }
}