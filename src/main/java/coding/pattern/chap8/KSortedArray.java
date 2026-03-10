package coding.pattern.chap8;

import java.util.PriorityQueue;

public class KSortedArray {

    public static int[] sort(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int l = 0; l <= k; l++) {
            heap.add(nums[l]);
        }

        int insert_index = 0;
        for(int i = k+1; i <nums.length; i++) {
            nums[insert_index++] = heap.poll();
            heap.add(nums[i]);
        }

        while (!heap.isEmpty()) {
            nums[insert_index++] = heap.poll();
        }

        return nums;
    }
}
