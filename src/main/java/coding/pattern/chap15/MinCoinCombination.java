package coding.pattern.chap15;

import java.util.HashMap;
import java.util.Map;

public class MinCoinCombination {

    public static int minCoinCombinationTopDown(int[] coins, int target) {
        int res = topDownDp(coins, target, new HashMap<Integer, Integer>());
        return res;
    }

    private static int topDownDp(int[] coins, int target, Map<Integer, Integer> memo) {
        // base case: if the target is 0, then 0 coins are need to reach it
        if (target == 0) {
            return 0;
        }
        if(memo.containsKey(target)) {
            return memo.get(target);
        }
        int min_coins =  Integer.MAX_VALUE;
        for (int coin : coins) {
            // avoid negative targets
            if (target >= coin) {
                // calculate the minimum number of coins needed if we use the current coin
                min_coins = Math.min(min_coins, 1 + topDownDp(coins, target-coin, memo));
            }
        }
        memo.put(target, min_coins);
        return min_coins;
    }

    public static int minCoinCombinationBottomUp(int[] coins, int target) {
        int[] dp = new int[target + 1];
        for (int i = 0; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i = 1; i <= target; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
                }
            }
        }
        return dp[target] != Integer.MAX_VALUE ? dp[target] : -1;
    }
}
