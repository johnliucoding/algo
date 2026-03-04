package coding.pattern.chap1;

import java.util.Objects;

public class MoveZero {

    public static int[] moveToEnd(int[] nums) {
        Objects.requireNonNull(nums, "nums must not be null");
        if(nums.length < 2) {
            return nums;
        }
        int to = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                swap(nums, to, i);
                // increment 'to' since it now points to a position already
                // occupied by a non-zero element.
                to++;
            }
        }
        return nums;
    }
    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
