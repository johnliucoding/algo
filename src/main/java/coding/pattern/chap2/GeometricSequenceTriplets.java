package coding.pattern.chap2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * @author liuxiaodong02
 */
public class GeometricSequenceTriplets {

    private static final Logger log = LoggerFactory.getLogger(GeometricSequenceTriplets.class);

    public static int bruteForceTriplets(int[] nums, int ratio) {
        if(nums == null || nums.length < 3) {
            return 0;
        }
        ArrayList<int[]> triplets = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if(nums[i] * ratio == nums[j] && nums[j] * ratio == nums[k]) {
                        triplets.add(new int[]{i, j, k});
                    }
                }
            }
        }
        log.atInfo().setMessage("found triplet indexes: {}")
                .addArgument(() -> triplets.stream().map(Arrays::toString).toList()).log();
        return triplets.size();
    }

    public static int bruteForceTriplets2(int[] nums, int ratio) {
        if(nums == null || nums.length < 3) {
            return 0;
        }
        ArrayList<int[]> triplets = new ArrayList<>();

        for (int i = 1; i < nums.length; i++) {
            int left = i - 1;
            int right = i + 1;

            ArrayList<Integer> leftList = new ArrayList<>();
            ArrayList<Integer> rightList = new ArrayList<>();
            while(left >=0 ) {
                if(nums[left] *ratio == nums[i]) {
                    leftList.add(left);
                }
                left--;
            }
            while(right < nums.length) {
                if(nums[i] *ratio == nums[right]) {
                    rightList.add(right);
                }
                right++;
            }

            if(!leftList.isEmpty() && !rightList.isEmpty()) {
                for (Integer leftIdx : leftList) {
                    for (Integer rightIdx : rightList) {
                        triplets.add(new int[]{leftIdx, i, rightIdx});
                    }
                }
            }
        }
        log.atInfo().setMessage("found triplet indexes: {}")
                .addArgument(() -> triplets.stream().map(Arrays::toString).toList()).log();
        return triplets.size();
    }

    // O(n)
    public static int mapCase(int[] nums, int ratio) {
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        for (int num : nums) {
            // left map with frequency of each element of 0
            left.merge(num, 0, Integer::sum);
            // right map with frequency of each element in the array
            right.merge(num, 1, Integer::sum);
        }
        log.atInfo().setMessage("left map: {}").addArgument(left).log();
        log.atInfo().setMessage("right map: {}").addArgument(right).log();

        int account = 0;
        // search for geometric triplets that has num as the middle
        for (int num : nums) {
            // decrement the frequency of num in right map since num is now
            // being processed and is no longer to the right
            right.merge(num, -1, Integer::sum);
            if( num % ratio == 0 ) {
                // default value
                account += (left.getOrDefault(num / ratio, 0) * right.getOrDefault(num * ratio, 0));
            }
            // increment the frequency of num in left map since it'll be a part
            // of the left side of the array once we iterate to the next value of nums
            left.merge(num, 1, Integer::sum);
        }
        return account;
    }
}
