package coding.pattern.chap15;

import java.util.Arrays;

/**
 * @author liuxiaodong02
 */
public class Knapsack {

    public static int maxValue(int cap, int[] weights, int[] values) {
        int n = values.length;
        // base case: set the last row and first column to 0 by initializing the entire DP table to 0
        int[][] dp = new int[n + 1][cap + 1];

        // populate the DP table
        for (int i = n-1; i >= 0 ; i--)  {
            for (int c = 1; c < cap + 1; c++) {
                // if the item 'i' fits in the current knapsack capacity
                // the maximum value at 'dp[i][c]' is the largest of either:
                // 1. the maximum value if we include item 'i'
                // 2. the maximum value if we exclude item 'i'
                if(weights[i] <= c){
                    dp[i][c] = Math.max(values[i] + dp[i+1][c-weights[i]], dp[i+1][c]);
                } else {
                // if it doesn't fit, we have to exclude it.
                    dp[i][c] = dp[i+1][c];
                }
            }
        }

        IO.println(matrixString(dp));
        return dp[0][cap];
    }
    private static String matrixString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(j == matrix[i].length - 1) {
                    sb.append(matrix[i][j]);
                } else {
                    sb.append(matrix[i][j]).append(" ");
                }
            }

            sb.append("\n");

        }
        sb.append("-".repeat(matrix.length * 2));
        return sb.toString();
    }
    public static int maxValueOptimized(int cap, int[] weights, int[] values) {
        int n = values.length;
        int[] pre_row = new int[cap + 1];
//        int[] curr_row = new int[cap + 1]; // 这里有个非常严重的bug

        for (int i = n-1; i >= 0 ; i--) {
            int[] curr_row = new int[cap + 1];
            for (int c = 1; c < cap + 1; c++) {
                if(weights[i] <= c){
                    curr_row[c] = Math.max(values[i] + pre_row[c-weights[i]], pre_row[c]);
                } else  {
                    curr_row[c] = pre_row[c];
                }
            }
            pre_row = curr_row; //
            IO.println(Arrays.toString(curr_row));
        }
        return pre_row[cap];
    }
}
