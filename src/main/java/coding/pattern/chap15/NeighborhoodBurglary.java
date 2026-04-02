package coding.pattern.chap15;

/**
 * @author liuxiaodong02
 */
public class NeighborhoodBurglary {

    public static int maxMoney(int[] houses) {
        // handle the cases when the array is less than the size of 2 to
        // avoid out-of-bounds errors when assigning the base case values
        if(houses==null || houses.length==0) {
            return 0;
        }
        if(houses.length==1) {
            return houses[0];
        }

        int[] dp = new int[houses.length];
        // base case: when there's only one house, rob that house
        dp[0] = houses[0];
        // base case: when there are two houses, rob the one the most money
        dp[1] = Math.max(houses[0], houses[1]);
        // fill in the rest of the DP array
        for(int i = 2; i < houses.length; i++) {
            // 'dp[i]' = max(profit if we skip house i, profit if we rob house i)
            dp[i] = Math.max(dp[i-1], dp[i-2] + houses[i]);
        }
        return dp[houses.length-1];
    }

    public static int maxMoneyOptimized(int[] houses) {
        // handle the cases when the array is less than the size of 2 to
        // avoid out-of-bounds errors when assigning the base case values
        if(houses==null || houses.length==0) {
            return 0;
        }
        if(houses.length==1) {
            return houses[0];
        }


        // base case: when there's only one house, rob that house
        int prev_prev = houses[0];
        // base case: when there are two houses, rob the one the most money
        int prev = Math.max(houses[0], houses[1]);
        // fill in the rest of the DP array
        for(int i = 2; i < houses.length; i++) {
            // 'dp[i]' = max(profit if we skip house i, profit if we rob house i)
            int curr = Math.max(prev, prev_prev + houses[i]);
            prev_prev = prev;
            prev = curr;
        }
        return prev;
    }
}
