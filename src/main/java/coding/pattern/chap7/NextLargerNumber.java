package coding.pattern.chap7;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author liuxiaodong02
 */
public class NextLargerNumber {

    static class VL {
        int value;
        int index;
        public VL(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static int[] find(int[] nums) {

        int[] result =  new int[nums.length];
        Arrays.fill(result, -1);
        var stack = new ArrayDeque<VL>();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek().value < nums[i]) {
                VL top = stack.pop();
                result[top.index] = nums[i];
            }
            stack.push(new VL(nums[i],i));
        }

        return result;
    }

    // time O(n)
    // space O(n)
    public static int[] find2(int[] nums) {
        int[] result =  new int[nums.length];
        var stack = new ArrayDeque<Integer>();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return result;
    }
}
