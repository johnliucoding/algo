package coding.pattern.chap10;

import java.util.HashMap;

/**
 * @author liuxiaodong02
 */
public class FindSubarray {

    // time O(n^2)
    public static int subarraySumToK(int[] nums, int k) {

        int[] prefix_sum = new int[nums.length+1];
        prefix_sum[0] = 0;
        prefix_sum[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix_sum[i+1] = prefix_sum[i] + nums[i];
        }
        int count = 0;
        for (int i = 1; i < nums.length+1; i++) {
            for (int j = i+1; j < nums.length+1; j++) {
                if (prefix_sum[j] - prefix_sum[i-1] == k) {
                    count++;
                }
            }
        }

        return count;
    }

    // time O(n)
    public static int subarraySumToK2(int[] nums, int k) {
        // initialize the map with 0 to handle subarrays that sum to 'k'
        // from the start of the array
        HashMap<Integer, Integer> sum_freq =  new HashMap<>();
        sum_freq.put(0, 1); // sum 0, freq 1

        int count = 0;

        int current_sum = 0;
        for (int num : nums) {
            // update the running prefix sum by adding the current number.
            current_sum += num;
            // if a subarray with sum 'k' exists, increment 'count' by the number
            // of times it has been found
            int num_in_map = sum_freq.getOrDefault(current_sum - k, 0);
            count += num_in_map;
            // update the frequency of 'current_sum' in th hash map.
            sum_freq.merge(current_sum, 1, Integer::sum);
        }
        return count;
    }
}
