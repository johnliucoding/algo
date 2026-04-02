package coding.pattern.chap15;

import java.util.HashMap;
import java.util.Map;

public class MinCoinCombination {
    static final int INF = 0x3f3f3f3f;
    public static int minCoinCombinationTopDown(int[] coins, int target) {
        int res = topDownDp(coins, target, new HashMap<Integer, Integer>());
        return res == INF ? -1 : res;
    }

    private static int topDownDp(int[] coins, int target, Map<Integer, Integer> memo) {
        // base case: if the target is 0, then 0 coins are need to reach it
        if (target == 0) {
            return 0;
        }
        if(memo.containsKey(target)) {
            return memo.get(target);
        }
        int min_coins =  INF;
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
            dp[i] = INF;
        }
        dp[0] = 0;

        for (int t = 1; t <= target; t++) {
            for (int coin : coins) {
                if (coin <= t) {
                    dp[t] = Math.min(dp[t], 1 + dp[t-coin]);
                }
            }
        }
        return dp[target] != INF ? dp[target] : -1;
    }
}

// Integer.MAX_VALUE 计算有溢出问题
//Integer.MAX_VALUE + 1
//Double.POSITIVE_INFINITY + 1 结果是不一样的