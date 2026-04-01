package coding.pattern.chap15;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxiaodong02
 */
public class ClimbingStair {
    private final Map<Integer, Integer> memo = new HashMap<>();
    public int climbStairsTopDown(int n) {
        // base case: with 1-step staircase, there is only one way to climb it
        // with 2-step staircase, there are two ways to climb it
        if(n <= 2) {
            return n;
        }
        if(memo.containsKey(n)) {
            return memo.get(n);
        }
        // the number of ways to climb to the n-th step is equal to the sum
        // of the number of ways to climb to step n-1 and to n-2
        memo.put(n,climbStairsTopDown(n-1) +  climbStairsTopDown(n-2));
        return memo.get(n);
    }

    public int climbStairsBottomUp(int n) {
        if(n <= 2) {
            return n;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int climbStairsBottomUpOptimized(int n) {
        if(n <= 2) {return n;}
        int one_step_before = 2;
        int two_step_before = 1;
        for (int i = 3; i < n+1; i++) {
            int current = one_step_before + two_step_before;
            two_step_before = one_step_before;
            one_step_before = current;
        }
        return one_step_before;
    }
}
