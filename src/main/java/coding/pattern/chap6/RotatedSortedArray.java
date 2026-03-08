package coding.pattern.chap6;

public class RotatedSortedArray {

    public static int findTarget(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left  + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            // if the left subarray [left : mid] is sorted
            } else if (nums[left] <= nums[mid]) {
                // check if the target falls in sorted left subarray
                // if it does, search the left subarray. otherwise, search
                // the right
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            // if the right subarray [mid : right] is sorted
            } else {
                // check if the target falls in this subarray,
                // if it does, search the right subarray, otherwise,
                // search the left
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        // if the target is found in the array, return it's index
        // otherwise, return -1
        return nums[left] == target ? left : -1;
    }
}
