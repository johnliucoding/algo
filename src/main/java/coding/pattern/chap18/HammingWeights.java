package coding.pattern.chap18;

public class HammingWeights {

    public static int[] hammingWeights(int n) {
        int[] res = new int[n+1];

        for (int i = 0; i < n+1; i++) {
            res[i] = weights(i);
        }
        return res;
    }

    public static int[] hammingWeightsDp(int n) {
        // base case: the number of set bits in 0 is just 0
        // we set dp[0] to 0 by initializing the entire DP array to 0
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            // 'dp[i]' is obtained using the result of 'dp[i >> 1]', plus the LSB of 'i'
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    private static int weights(int n) {
        int count = 0;
        // count each set bit of 'n' until 'n' equals 0
        while (n > 0) {
            // increment the count if the LSB is 1
            count += n & 1;
            // unsigned right shift 'n' to shift the next bit to the LSB position
            n = n >>> 1;
        }
        return count;
    }
}
