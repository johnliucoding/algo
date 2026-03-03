package coding.pattern.chap1;

import java.util.Objects;

public class PairSumSorted {

    public static int[] bruteForce(int[] nums, int target) {
        // if null
        Objects.requireNonNull(nums, "nums cannot be null");
        // if length is 0
        if(nums.length == 0) {
            return new int[]{};
        }
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // time O(n)
    // space O(1)
    public static int[] twoPointer(int[] nums, int target) {
        // if null
        Objects.requireNonNull(nums, "nums cannot be null");
        // if length is 0
        if(nums.length == 0) {
            return new int[]{};
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            // if the target pair is found, return its indexes
            if(sum == target) {
                return new int[]{left, right};
            // if the sum is smaller, increment the left pointer, aiming to increase
            // the sum toward the target value.
            } else if (sum < target) {
                left++;
            // if the sum is larger, decrement the right pointer, aiming to decrease
            // the sum toward the target value.
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}
