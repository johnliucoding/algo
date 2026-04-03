package coding.pattern.chap15;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class LargestSquareInMatrix {

    public static int largestSquare(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        int max_len = 0;
        for (int c = 0; c < cols; c++) {
            if(matrix[0][c] == 1) {
                dp[0][c] = 1;
                max_len = 1;
            }
        }
        for (int r = 0; r < rows; r++) {
            if(matrix[r][0] == 1) {
                dp[r][0] = 1;
                max_len = 1;
            }
        }

        for (int c = 1; c < cols; c++) {
            for (int r = 1; r < rows; r++) {
                if(matrix[r][c] == 1) {
                    List<Integer> candidates = Arrays.asList(dp[r - 1][c], dp[r][c - 1], dp[r - 1][c - 1]);
                    candidates.sort(Integer::compareTo);
                    dp[r][c] = 1 + candidates.getFirst();
                }
                max_len = Math.max(max_len, dp[r][c]);
            }
        }
        return Math.powExact(max_len, 2);
    }

    public static int largestSquareOptimized(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] prev = new int[cols];
        int max_len = 0;
//        for (int c = 0; c < cols; c++) {
//            if(matrix[0][c] == 1) {
//                prev[c] = 1;
//                max_len = 1;
//            }
//        }

        for (int r = 1; r < rows; r++) {
            int[] cur =  new int[cols];
            for (int c = 0; c < cols; c++) {
                if(c == 0) {
                    cur[c] = matrix[r][c];
                } else if (matrix[r][c] == 1) {
                    List<Integer> candidates = Arrays.asList(prev[c], cur[c - 1], prev[c - 1]);
                    candidates.sort(Integer::compareTo);
                    cur[c] = 1 + candidates.getFirst();
                }
                max_len = Math.max(max_len, cur[c]);
            }
            prev = cur;
        }

        return Math.powExact(max_len, 2);

    }
}
