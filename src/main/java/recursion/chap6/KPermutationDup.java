package recursion.chap6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KPermutationDup {
    public static List<int[]> permute(int[] nums, int k) {
        if(k > nums.length) {
            throw new IllegalArgumentException("k should not be greater than nums.length");
        }
        ArrayList<int[]> result = new ArrayList<>();
        int[] current = new int[k];
        permute(nums, k, 0, current, result);
        return result;
    }
    private static void permute(int[] nums, int k, int len, int[] current, List<int[]> result) {
        if (len == k) {
            result.add(Arrays.copyOf(current, current.length));
            return;
        }

        for (int num : nums) {
            current[len] = num;
            permute(nums, k, len + 1, current, result);
            current[len] = 0;
        }
    }
}
