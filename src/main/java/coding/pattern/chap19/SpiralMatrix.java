package coding.pattern.chap19;

public class SpiralMatrix {

    public static int[] traverse(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows*cols];
        int index = 0;
        // initialize the matrix boundaries
        int top = 0;
        int bottom = rows-1;
        int left = 0;
        int right = cols-1;
        // traverse the matrix in spiral order
        while(top <= bottom && left <= right) {
            // move from left to right along the top boundary
            for(int i = left; i <= right; i++) {
                result[index++] = matrix[top][i];
            }
            top++;
            // move from top to bottom along the right boundary
            for(int i = top; i <= bottom; i++) {
                result[index++] = matrix[i][right];
            }
            right--;
            // check that the bottom boundary hasn't passed the top boundary
            // before moving from the right to left along the bottom boundary
            if(top <= bottom) {
                for(int i = right; i >= left; i--) {
                    result[index++] = matrix[bottom][i];
                }
                bottom--;
            }
            // check that the left boundary hasn't passed the right boundary before
            // moving from bottom to top along the left boundary
            if(left <= right) {
                for(int i = bottom; i >= top; i--) {
                    result[index++] = matrix[i][left];
                }
                left++;
            }
        }
        return result;
    }
}
