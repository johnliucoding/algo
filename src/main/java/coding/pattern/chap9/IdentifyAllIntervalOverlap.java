package coding.pattern.chap9;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class IdentifyAllIntervalOverlap {

    public static List<Interval> overlaps(List<Interval> intervals1, List<Interval> intervals2) {
        ArrayList<Interval> result = new ArrayList<>();
        for (int i = 0, j = 0; i <intervals1.size() && j < intervals2.size(); ) {

            // determine which interval start first
            Interval a, b;
            if (intervals1.get(i).start() <= intervals2.get(j).start()) {
                 a =  intervals1.get(i);
                 b =  intervals2.get(j);
            } else {
                a = intervals2.get(j);
                b = intervals1.get(i);
            }

            // check if overlap
            if(a.end() >= b.start()) {
                result.add(
                new Interval(b.start(), Math.min(a.end(), b.end())));
            }

            // check which pointer to advance
            if(intervals1.get(i).end() > intervals2.get(j).end()) {
                j++;
            } else if(intervals1.get(i).end() == intervals2.get(j).end()) {
                i++;
                j++;
            } else {
                i++;
            }

        }
        return result;
    }
}
