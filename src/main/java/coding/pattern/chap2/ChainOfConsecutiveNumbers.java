package coding.pattern.chap2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ChainOfConsecutiveNumbers {

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
