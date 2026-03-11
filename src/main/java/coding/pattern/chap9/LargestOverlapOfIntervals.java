package coding.pattern.chap9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author liuxiaodong02
 */
public class LargestOverlapOfIntervals {
    private static final Logger log = LoggerFactory.getLogger(LargestOverlapOfIntervals.class);

    enum PointType {
        START, END
    }
    record Point(int index, PointType type) {}

    public static int largestOverlap(List<Interval> intervals) {

        List<Point> pointList = intervals.stream()
                // change to point
                .flatMap(interval ->
                        Stream.of(new Point(interval.start(), PointType.START),
                                new Point(interval.end(), PointType.END)))
                // sort the point list
                .sorted(Comparator.comparing(Point::index)
                        .thenComparing(Comparator.comparing(Point::type).reversed()))
                .toList();

        log.atInfo().setMessage("sorted points: {}").addArgument(pointList).log();

        // process the point list
        int max_overlaps = 0;
        int active_intervals = 0;
        for (Point point : pointList) {
            if(point.type.equals(PointType.START)) {
                active_intervals++;
            } else {
                active_intervals--;
            }
            max_overlaps = Math.max(max_overlaps, active_intervals);
        }
        return  max_overlaps;
    }
}
