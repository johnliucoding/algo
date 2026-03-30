package coding.pattern.chap13;

/**
 * @author liuxiaodong02
 */
public class LongestIncreasingPath {

    public static int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] memo = new int[rows][cols];

        // find the longest increasing path starting at each cell.
        // the maximum of these is equal to the overall longest increasing
        // path
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                res = Math.max(res, dfs(r, c, matrix ,memo));
            }
        }


        return res;
    }

    private static int dfs(int r, int c, int[][] matrix, int[][] memo) {
        if (memo[r][c] != 0) return memo[r][c];
        int max = 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // the longest path starting at the current cell is equal to the
        // longest path of its larger neighboring cells, plus 1
        for (int[] dir : dirs) {
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(withinBounds(newR, newC, matrix)
            && matrix[newR][newC] > matrix[r][c]) {
                max = Math.max(max, 1+dfs(newR, newC, matrix, memo));
            }
        }
        memo[r][c] = max;
        return max;
    }

    private static boolean withinBounds(int r, int c, int[][] matrix) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length;
    }
}
