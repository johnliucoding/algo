package coding.pattern.chap6;

public class FirstLastOccurrence2 {

    public static int[] indexes(int[] nums, int target) {
        int lower_bound = find_lower_bound(nums, target);
        int upper_bound = find_upper_bound(nums, target);
        return new int[]{lower_bound, upper_bound};
    }
    private static int find_lower_bound(int[] nums, int target) {
        if(nums == null || nums.length < 1) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }
    private static int find_upper_bound(int[] nums, int target) {
        if(nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // in upper-bound binary search, bias the midpoint to the right
            int mid = left + (right - left) / 2 + 1;
            if(nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                left = mid;
            }
        }
        // if the target doesn't exist in the array, then it's possible that
        // 'left = mid + 1' places the left pointer outside the array when
        // 'mid == n - 1'. So, we use the right pointer in the return statement instead
        return nums[right] == target ? right : -1;
    }

}
