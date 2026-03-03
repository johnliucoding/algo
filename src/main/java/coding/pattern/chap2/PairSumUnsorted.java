package coding.pattern.chap2;

import java.util.HashMap;

/**
 * @author liuxiaodong02
 * @date 2026/3/3 16:39
 */
public class PairSumUnsorted {

    public static int[] twoPass(int[] nums, int target) {
        var num_map = new HashMap<Integer, Integer>();

        // pass 1: populate number and index map
        for (int i = 0; i < nums.length; i++) {
            num_map.put(nums[i], i);
        }

        // pass 2: check for each number's complement in the hash map
        for (int i = 0; i < nums.length; i++) {
            var complement = target - nums[i];
            if (num_map.containsKey(complement) && num_map.get(complement) != i) {
                return new int[]{i, num_map.get(complement)};
            }
        }
        return new int[]{};
    }

    // time O(n)
    // space O(n)
    public static int[] onePass(int[] nums, int target) {
        var num_map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            var complement = target - nums[i];
            if (num_map.containsKey(complement)) {
                return new int[]{num_map.get(complement), i};
            }
            num_map.put(nums[i], i);
        }
        return new int[]{};
    }
}
