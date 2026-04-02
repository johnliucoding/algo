package coding.pattern.chap15;

import java.util.Arrays;

/**
 * @author liuxiaodong02
 */
public class MatrixPathways {

    public static int matrixPathways(int m, int n) {
        // base case: set all cells in row 0 and column 0 to 1
        // we can do this by initializing all cells in the DP table to 1
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = 1;
            }
        }
        // fill in the rest of the DP tables
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // paths to current cell = paths from above + paths form left
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int matrixPathwaysOptimized(int m, int n) {
        // initialize 'prev_row' as the DP values of row 0, which are all 1s
        int[] previous_row =  new int[n];
        Arrays.fill(previous_row, 1);

        // set the first cell of 'cur_row' to 1.
        // this is done by setting the entire row to 1
        int[] cur_row =  new int[n];
        Arrays.fill(cur_row, 1);

        for (int r = 1; r < m; r++) {
            // the number of unique paths to the current cell is the sum of
            // the paths from the cell above it 'previous_row[c]' and the cell to the left 'cur_row[c-1]'
            for (int c = 1; c < n; c++) {
                cur_row[c] = previous_row[c] + cur_row[c-1];
            }
            //update 'previous_row' with 'cur_row' values for the next iteration
            previous_row = cur_row;

        }
        // the last element in 'previous_row' stores the result for the bottom-right cell
        return previous_row[n - 1];

    }
}
