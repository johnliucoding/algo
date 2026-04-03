package coding.pattern.chap15;

/**
 * @author liuxiaodong02
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(String s1, String s2) {

        // base case: set the last row and last column to 0 by initializing
        // the entire DP table with 0s
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        // populate the DP table
        for (int i = m-1; i >=0; i--) {
            for (int j = n-1; j >=0; j--) {
                // if the characters match, the length of the LCS at
                // 'dp[i][j]' is 1 + the LCS length of the remaining substrings
                if(s1.charAt(i)==s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1] + 1;
                // if the characters don't match, the LCS length at 'dp[i][j]' can
                // be found by either
                //  1 excluding the current character of s1
                //  2 excluding the current character of s2
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        return dp[0][0];
    }

    public static int longestCommonSubsequenceOptimized(String s1, String s2) {

        // base case: set the last row and last column to 0 by initializing
        // the entire DP table with 0s
        int m = s1.length();
        int n = s2.length();
        // initializing pre row as the DP values of the last row
        int[] previous = new int[n+1];
        // set the last cell of cur row to 0 to set the base case for this row
        // this is done by initializing the entire row to 0
        int[] current = new int[n+1];



        for (int i = m-1; i >=0; i--) {
            for (int j = n-1; j >=0; j--) {
                // if the characters match, the length of the LCS at
                // 'current[j]' is 1 + the LCS length of the remaining substrings
                if(s1.charAt(i)==s2.charAt(j)) {
                    current[j] = previous[j+1] + 1;
                    // if the characters don't match, the LCS length at 'current[j]' can
                    // be found by either
                    //  1 excluding the current character of s1
                    //  2 excluding the current character of s2
                } else {
                    current[j] = Math.max(previous[j],current[j+1]);
                }
            }
            // update previous with current values for the next iteration
            previous = current;
        }

        return previous[0];
    }
}
