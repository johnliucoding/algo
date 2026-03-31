package coding.pattern.chap14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class Permutation {

    public static List<int[]> permutation(int[] nums) {
        var result = new ArrayList<int[]>();

       backtrack(nums, new ArrayList<Integer>(), new HashSet<Integer>(), result);
        return result;
    }

    private static void backtrack(int[] nums, ArrayList<Integer> candidate, HashSet<Integer> used, ArrayList<int[]> result) {
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
                candidate.remove(candidate.size()-1);
                used.remove(num);
            }
        }
    }
}
