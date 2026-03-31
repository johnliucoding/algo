package coding.pattern.chap14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class Subsets {


    public static List<int[]> subsets(int[] nums) {
        var result = new ArrayList<int[]>();

        backtrack(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<Integer> list, ArrayList<int[]> result) {
        // base case: if all elements have been considered, add
        // the current subset to the output
        if (index == nums.length) {
            result.add(list.stream().mapToInt(i -> i).toArray());
            return;
        }
        // include the current element and recursively explore all paths that branch from this subset
        list.add(nums[index]);
        backtrack(nums, index + 1, list, result);
        // exclude the current element and recursively explore all paths
        // that branch from this subset
        list.remove(list.size() - 1);
        backtrack(nums, index + 1, list, result);
    }
}
