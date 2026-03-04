package coding.pattern.chap1;

import java.util.Objects;

/**
 * @author liuxiaodong02
 */
public class LargestContainer {

    // O(n^2)
    public static int bruteForce(int[] nums) {
        Objects.requireNonNull(nums, "nums must not be null");
        if(nums.length < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int current = Math.min(nums[i], nums[j]) * (j - i);
                max = Math.max(max, current);
            }
        }
        return max;
    }

    public static int pointer(int[] nums) {
        Objects.requireNonNull(nums, "nums must not be null");
        if(nums.length < 2) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int max = 0;
        while (left < right) {
            // calculate the water contained between the current pair of lines
            int current = Math.min(nums[left], nums[right]) * (right - left);
            max = Math.max(max, current);
            // move pointer inward, always moving the pointer at the shorter line.
            // if both lines have the same height, move both pointer inward.
            if(nums[left] < nums[right]) {
                left++;
            } else if (nums[left] > nums[right]) {
                right--;
            }  else {
                left++;
                right--;
            }
        }
        return max;
    }
}
