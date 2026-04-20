package recursion.chap6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KPermutation {

    public static List<int[]> permute(int[] nums, int k) {
        if(k > nums.length) {
            throw new IllegalArgumentException("k should not be greater than nums.length");
        }
        ArrayList<int[]> result = new ArrayList<>();
        permute(nums, k, 0, result);
        return result;
    }

    private static void permute(int[] nums, int k, int len, List<int[]> result) {
        if(len == k) {
            int[] ints = Arrays.copyOfRange(nums, 0, k);
            result.add(ints);
            return;
        }

        for (int i = len; i < nums.length; i++) {
           swap(nums, i, len);
           permute(nums, k, len + 1, result);
           swap(nums, i, len);
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
