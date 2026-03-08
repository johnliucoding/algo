package coding.pattern.chap6;

public class InsertionIndex {

    public static int notRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        if(nums[left] < target) {
            return left + 1;
        } else {
            return left;
        }

    }

    // objective: finding lower bound of values that satisfy `nums[i] >= target`
    // search space: [0, len(nums)]
    // how to narrow the search space:
    // 1 the midpoint is greater than or equal to the target, indicating the lower bound is either at the midpoint
    // or to its left `right = mid`
    // 2 the midpoint is less than the target, indicating the lower bound is somewhere to the right
    // `left = mid + 1`
    public static int insertionIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // search space include after last element
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
