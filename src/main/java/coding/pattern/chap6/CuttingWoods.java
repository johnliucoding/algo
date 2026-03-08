package coding.pattern.chap6;

public class CuttingWoods {

    private static int max(int[] heights) {
        int max = 0;
        for (int height : heights) {
            max = Math.max(max, height);
        }
        return max;
    }
    // determine if the current value of 'h' cuts at least 'k' meters of
    // wood
    private static boolean enough_wood(int[] heights, int k, int h) {
        int wood = 0;
        for (int height : heights) {
            if(height > h) {
                wood += height - h;
            }
        }
        return wood >= k;
    }
    public static int cutting(int[] heights, int k) {
        int left = 0;
        int right = max(heights);
        while (left < right) {
            // bias the midpoint to the right during the upper-bound binary search
            int mid = left + (right -left) / 2 + 1;
            if(enough_wood(heights, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}
