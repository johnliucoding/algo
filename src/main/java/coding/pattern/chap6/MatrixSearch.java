package coding.pattern.chap6;

public class MatrixSearch {


    public static int[] search(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int left = 0;
        int right = row * col - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int mid_val = matrix[mid / row][mid % row];
            if(mid_val == target) {
                return new int[]{mid / row, mid % row};
            } else if(mid_val < target) {
                left = mid  + 1;
            } else {
                right = mid - 1;
            }
        }
        return matrix[left / row][left % row] == target ? new int[]{left / row, left % row} :new int[]{-1, -1};
    }
}
