package coding.pattern.chap13;

import java.util.ArrayDeque;

public class MatrixInfection {

    public static int infect(int[][] matrix) {
        int step = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        var queue = new ArrayDeque<int[]>();
        int ones = 0;
        // count the total number of uninfected cells and add each infected cell to the queue
        // to represent level 0 of the level order traversal
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] == 1) {
                    ones++;
                } else if  (matrix[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                }
            }
        }
        // use level-order traversal to determine how long it takes to infect the uninfected cells
        while (!queue.isEmpty() && ones > 0) {
            // 1 step passes with each level of the matrix that's explored
            step++;
            int queueSize = queue.size(); // this need store outside the loop
            // infect any neighboring 1s and add them to the queue to be processed in the next level
            for (int i = 0; i < queueSize; i++) {
                int[] poll = queue.poll();
                assert poll != null;
                int r = poll[0];
                int c = poll[1];
                for (int[] direction : directions) {
                    int nextR = r + direction[0];
                    int nextC = c + direction[1];
                    if(withinBounds(nextR, nextC, matrix)
                    && matrix[nextR][nextC] == 1) {
                        matrix[nextR][nextC] = 2;
                        ones--;
                        queue.offer(new int[]{nextR, nextC});
                    }
                }
            }
        }

        // if there are still uninfected cells left, return -1
        // otherwise return the time passed
        return ones == 0 ? step : -1;
    }

    private static boolean withinBounds(int r, int c, int[][] matrix) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length;
    }
}
