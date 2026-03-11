package coding.pattern.chap10;

/**
 * @author liuxiaodong02
 */
public class SumBetweenRange {

    private final int[] original;
    private final int[] prefix_sum;

    public SumBetweenRange(int[] nums) {
        this.original = nums;
        this.prefix_sum = new int[nums.length];
        prefix_sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + nums[i];
        }
    }

    public int sumBetween(int from, int to) {
        if(from == 0) {
            return prefix_sum[to];
        } else {
            return prefix_sum[to] - prefix_sum[from - 1];
        }
    }
}
