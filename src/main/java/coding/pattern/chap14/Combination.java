package coding.pattern.chap14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class Combination {


    public static List<int[]> combination(int[] nums, int target) {
        var result = new ArrayList<int[]>();

        var candidates = new ArrayList<Integer>();

        dfs(candidates, 0, nums, target, result);

        return result;
    }

    private static void dfs(ArrayList<Integer> candidates, int start, int[] nums, int target, ArrayList<int[]> result) {
        // termination condition: if the target is equal to 0, we found a combination that sums to target
        if(target == 0) {
            result.add(candidates.stream().mapToInt(i -> i).toArray());
            return;
        }
        // termination condition: if the target is less than 0, no more valid combinations can be created by adding
        //  to the current combination
        if(target < 0) {
            return;
        }

        // starting from start, explore all combinations after adding nums[i]
        for (int i = start; i < nums.length; i++) {
            // add the current number to create a new combination
            candidates.add(nums[i]);
            // recursively explore all paths that branch from this new combination
            dfs(candidates, i, nums, target - nums[i], result);
            // backtrack by removing the number we just added
            candidates.removeLast();
        }
    }
}
