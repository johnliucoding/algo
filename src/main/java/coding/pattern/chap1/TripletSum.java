package coding.pattern.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author liuxiaodong02
 * @date 2026/3/4 14:23
 */
public class TripletSum {
    // a + b + c = 0
    // sort nums
    // a = nums[i]
    // b + c = -a

    // time sort O(n*log(n)) + nested looping O(n^2) = O(n^2)

    public static List<int[]> tripletSum(int[] nums) {
        // if null
        Objects.requireNonNull(nums, "nums cannot be null");
        // if length is 0
        if(nums.length < 3) {
            return new ArrayList<>();
        }

        ArrayList<int[]> triplets = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // optimization: triplets consisting of only positive numbers will never sum to 0
            if(nums[i] > 0) {
                break;
            }
            // to avoid duplicate triplets, skip 'a' if it's the same as the previous number
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            // find all pairs that sum to a target of '-a'
            List<int[]> pairs = pairSumAllPairs(nums, i + 1, -nums[i]);
            for (int[] pair : pairs) {
                triplets.add(new int[]{nums[i], pair[0], pair[1]});
            }
        }
        return triplets;
    }

    private static List<int[]> pairSumAllPairs(int[] nums, int start, int target) {
        ArrayList<int[]> pairs = new ArrayList<>();

        int left = start;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                pairs.add(new int[]{nums[left], nums[right]});
                left++;
                // to avoid duplicate '[b, c]' pairs, skip 'b' if it's the same as the previous number
                while (left < right && nums[left] == nums[left-1]) {
                    left++;
                }
            } else if (sum < target) {
                left++;
            } else  {
                right--;
            }
        }
        return pairs;
    }
}
