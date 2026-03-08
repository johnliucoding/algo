package coding.pattern.chap6;

public class FirstLastOccurrence {

    public static int[] indexes(int[] nums, int target) {
        if(nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{-1, -1}; // 这里不对
        }

        int left = 0;
        int right = nums.length - 1;
        while (nums[left] < target || nums[right] > target) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else  if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // process left
                if(nums[left] < target) {
                    int left_mid = left + (mid - left) / 2;
                    while (nums[left_mid] == target) {
                        left_mid = left + (left_mid - left) / 2;
                    }
                    left = left_mid;
                }
                // process right
                if(nums[right] > target) {
                    int right_mid = mid + (right - mid) / 2;
                    while (nums[right_mid] == target) {
                        right_mid = right_mid + (right - right_mid) / 2;
                    }
                    right = right_mid;
                }
            }
        }
        return new int[] {left, right};
    }


    public static int[] indexes2(int[] nums, int target) {
        if(nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{-1, -1};  // 这里不对
        }
        // lower bound
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int lower_bound = left;

        // find upper bound
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1; //
            if(nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                left = mid;
            }
        }
        int upper_bound = left;

        return new int[]{lower_bound, upper_bound};
    }
}
