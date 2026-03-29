package coding.pattern.chap13;

import java.util.List;

public class CountIslands {



    public static int countIslands(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // if a land cell is found, perform DFS to explore the full
                // island, and include this island in our count
                if(matrix[i][j] == 1) {
                 dfs(i, j, matrix);
                 count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int i, int j, int[][] matrix) {
        // mark the current land cell as visited
        matrix[i][j] = -1;

        // define the direction vectors for up, down, left, and right
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // recursively call DFS on each neighboring land cell to continue
        // exploring this island
        for (int[] direction : dirs) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            if(withinBounds(nextI, nextJ, matrix) &&
                matrix[nextI][nextJ] == 1) {
                dfs(nextI, nextJ, matrix);
            }

        }
    }

    private static boolean withinBounds(int i, int j, int[][] matrix) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }
}
