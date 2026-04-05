package coding.pattern.chap19;

import java.util.HashMap;

public class MaxCollinearPoints {

    public static int maximumCollinearPoints(int[][] points) {
        int res = 0;

        // treat each point as a focal point, and determine the maximum number
        // of points that are collinear with each focal point. the largest of these maximums is the
        // answer
        for (int i = 0; i < points.length; i++) {
            res = Math.max(res, max_points_from_focal_point(i, points));
        }

        return res;
    }

    record Slop(int rise, int run) {}
    private static int max_points_from_focal_point(int i, int[][] points) {
        var map = new HashMap<Slop, Integer>();
        int max_points = 0;

        // for the current focal point, calculate the slope between it and every other point
        // this allows use to group points that share the same slope
        for (int j = 0; j < points.length; j++) {
            if(j != i) {
                var cur_slop = get_slop(points[i], points[j]);
                map.compute(cur_slop, (k, v) -> v == null ? 1 : v + 1);
                // update the maximum count of collinear points for the current focal point
                max_points = Math.max(max_points, map.get(cur_slop));
            }

        }
        // add 1 to the maximum count to include the focal point itself
        return max_points + 1;
    }

    private static Slop get_slop(int[] point1, int[] point2) {
        int rise = point1[1] - point2[1];
        int run = point1[0] - point2[0];
        // handle vertical lines separately to avoid dividing by 0
        if(run == 0) {
            return new Slop(1, 0);
        }
        // simplify the slop to its reduced form
        int gcd_val = gcd(rise, run);
        return new Slop(rise / gcd_val, run / gcd_val);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
