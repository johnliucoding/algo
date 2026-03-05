package coding.pattern.chap2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ChainOfConsecutiveNumbers {

    private static final Logger log = LoggerFactory.getLogger(ChainOfConsecutiveNumbers.class);

    // O(n*log(n))
    public static int bruteForce(int[] nums) {
        if(nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        log.atInfo().setMessage("sorted nums: {}").addArgument(nums).log();
        int longest = 0;
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            current++;

            if (i+1 == nums.length || nums[i] + 1 != nums[i + 1]) {
                longest = Math.max(current, longest);
                current = 0;
            }
        }
        return longest;
    }

    // O(n)
    public static int longestChainSize(int[] nums) {
        // not null
        // not empty array
        // no duplicates
        if(nums.length == 1) {
            return 1;
        }

        List<Integer> list = Arrays.stream(nums).boxed().toList();
        Set<Integer> intSet = Set.copyOf(list);
        int longest = 0;
        for (int num : nums) {
            // if the current number is the smallest number in its chain,
            // search for the length of its chain
            if(!intSet.contains(num -1)) {
                int current_num = num;
                int current_chain = 1;
                // continue to find the next consecutive number in the chain.
                while (intSet.contains(current_num + 1)) {
                    current_num++;
                    current_chain++;
                }
                longest = Math.max(longest, current_chain);
            }
        }
        return longest;

    }
}
