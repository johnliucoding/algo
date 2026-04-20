package recursion.chap6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KCombination {
    public static List<int[]> combine(int[] nums, int k) {
        if(k > nums.length) {
            throw new IllegalArgumentException("k should not be greater than nums.length");
        }
        ArrayList<int[]> result = new ArrayList<>();
        int[] current = new int[k];
        combine(nums, 0, 0,current, result);
        return result;
    }

    private static void combine(int[] nums, int position, int size,  int[] current, List<int[]> result) {
        if(size == current.length) {
            result.add(Arrays.copyOf(current, size));
            return;
        }

        for (int i = position; i < nums.length; i++) {
            current[size] = nums[i];
            combine(nums, i+1,  size + 1, current, result); //size++ 不行
            current[size] = 0;
        }
    }


}
