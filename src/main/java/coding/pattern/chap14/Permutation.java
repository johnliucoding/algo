package coding.pattern.chap14;

import java.util.*;

/**
 * @author liuxiaodong02
 */
public class Permutation {

    public static List<int[]> permutation(int[] nums) {
        var result = new ArrayList<int[]>();

//       backtrack(nums, new ArrayList<>(), new HashSet<>(), result);
       permutate(nums, 0, nums.length - 1, result);
        return result;
    }

    private static void backtrack(int[] nums,
                                  List<Integer> candidate,
                                  Set<Integer> used,
                                  List<int[]> result) {
        // if the current candidate is a complete permutation, add it to the result
        if(candidate.size() == nums.length) {
            result.add(candidate.stream().mapToInt(i -> i).toArray());
            return;
        }

        for (int num : nums) {
            if(!used.contains(num)) {
                // add 'num' to the current permutation and mark it as used
                candidate.add(num);
                used.add(num);
                // recursively explore all branches using the updated permutation candidate
                backtrack(nums, candidate, used, result);
                // backtrack by reversing the change made
                candidate.removeLast();
                used.remove(num);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void permutate(int[] nums, int left, int right, List<int[]> result) {
        if(left == right) {
            result.add(Arrays.copyOf(nums, nums.length));
            return;
        }
        for (int i = left; i <= right; i++) {
            // select one element 'i' to occupy left position
            swap(nums, left, i);
            // recur on rest position and element
            permutate(nums, left + 1, right, result);
            // back track
            swap(nums, left, i);
        }
    }
}
