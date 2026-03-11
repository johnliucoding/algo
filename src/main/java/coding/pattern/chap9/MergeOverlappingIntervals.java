package coding.pattern.chap9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class MergeOverlappingIntervals {

    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        intervals.stream()
                .sorted(Comparator.comparing(Interval::start))
                .forEach(interval -> {

                    if(!result.isEmpty() && result.getLast().end() >= interval.start()) {
                        // merge
                        int end = Math.max(result.getLast().end(), interval.end());
                        Interval new_interval = new Interval(result.getLast().start(), end);
                        result.removeLast();
                        result.add(new_interval);
                    } else {
                        result.add(interval);
                    }
        });


        return result;
    }

    public static List<Interval> mergeMutable(List<Interval> intervals) {
        // sort (change the original list)
        intervals.sort(Comparator.comparing(Interval::start));

        // add first
        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));

        // process rest
        for (Interval current : intervals.subList(1, intervals.size())) {
            if(result.getLast().end() < current.start()) {
                result.add(current);
            } else {
                int end = Math.max(result.getLast().end(), current.end());
                Interval new_interval = new Interval(result.getLast().start(), end);
                result.removeLast();
                result.add(new_interval);
            }
        }
        return result;
    }
}
