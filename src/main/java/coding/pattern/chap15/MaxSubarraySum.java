package coding.pattern.chap15;

/**
 * @author liuxiaodong02
 */
public class MaxSubarraySum {

    public static int maxSubarraySum1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // set the sum variable to negative MIN_VALUE to ensure negative sums can be considered
        int max_sum = Integer.MIN_VALUE;
        int current_sum = Integer.MIN_VALUE;
        // iterate through the array to find the maximum subarray sum
        for (int num : nums) {
            // either add the current number to the existing running sum, or start a new subarray
            // at the current number
            current_sum = Math.max(current_sum + num, num);
            max_sum = Math.max(max_sum, current_sum);
        }
        return max_sum;
    }

    public static int maxSubarraySum2(int[] nums) {
        if (nums == null || nums.length == 0) {return 0;}
        int[] dp = new int[nums.length];
        // base case: the maximum subarray sum of an array with just one element is that element
        dp[0] = nums[0];
        int max_sum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // determine the maximum subarray sum ending at the current index
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max_sum = Math.max(max_sum, dp[i]);
        }
        return max_sum;
    }

    public static int maxSubarraySum3(int[] nums) {
        if (nums == null || nums.length == 0) {return 0;}

        // base case: the maximum subarray sum of an array with just one element is that element
        int current_sum = nums[0];
        int max_sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // determine the maximum subarray sum ending at the current index
            current_sum = Math.max(current_sum + nums[i], nums[i]);
            max_sum = Math.max(max_sum, current_sum);
        }
        return max_sum;
    }
}
