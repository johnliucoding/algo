package coding.pattern.chap7;

import java.util.ArrayDeque;

/**
 * @author liuxiaodong02
 */
public class MaximumOfSlidingWindow {

    // time O(n)
    // space O(k)
    public static int[] maxSlidingWindow(int[] nums, int k) {

        // tuple data holder
        class VI {
            public int data;
            public int index;
            public VI(int data, int index) {
                this.data = data;
                this.index = index;
            }
        }
        int left = 0;
        int right = 0;
        var result = new int[nums.length - k + 1];
        var result_index = 0;
        var dp = new ArrayDeque<VI>();
        while (right <= nums.length - 1) {
            // 1) ensure the values of the deque maintain a monotonic decreasing order
            // by removing candidates <= the current candidate.
            while(!dp.isEmpty() && dp.peekLast().data <= nums[right]) {
                dp.pollLast();
            }
            // 2) add the current candidate
            dp.offerLast(new VI(nums[right], right));


            // if the window is of length 'k', record the maximum of the window
            if(right - left + 1 == k) {

                // remove values whose indexes occur outside the window
                if(!dp.isEmpty() && dp.peekFirst().index < left) {
                    dp.pollFirst();
                }
                // the maximum value of this window is the leftmost value in the deque
                result[result_index++] = dp.peekFirst().data;
                // slide the window by advancing both 'left' and 'right'. the right
                // pointer always gets advanced so we just need to advance 'left'
                left++;
            }
            right++;
        }
        return result;
    }
}
