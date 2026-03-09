package coding.pattern.chap6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liuxiaodong02
 */
public class WeightedRandomSelection {

    private static final Logger log = LoggerFactory.getLogger(WeightedRandomSelection.class);
    private final int[] weights;
    private final int[] prefix_sums;


    public WeightedRandomSelection(int[] weights) {
        this.weights = weights;
        this.prefix_sums = prefix_sum(weights);
        log.atInfo().setMessage("Weighted Random Selection Initialized, weights: {}, prefix sums: {}")
                .addArgument(weights).addArgument(prefix_sums).log();
    }
    private int[] prefix_sum(int[] nums) {
        int[] prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSums[i] = prefixSums[i-1] + nums[i];
        }
        return prefixSums;
    }

    public int select() {
        int len = prefix_sums.length;
        // pick a random target between 1 and largest possible endpoint, inclusive
        int target = ThreadLocalRandom.current().nextInt(1, this.prefix_sums[len - 1] + 1);
        log.atInfo().setMessage("random select value: {}").addArgument(target).log();

        int left = 0;
        int right = len - 1;

        // perform lower-bound binary search to find which prefix sum value correspond to the target
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(prefix_sums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
