package coding.pattern.chap2;

import java.util.HashSet;

public class ZeroStriping {

    // time O(m*n)
    // space O(m+n)
    public static void setStriping(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        var rowSet = new HashSet<Integer>();
        var colSet = new HashSet<Integer>();

        // traverse through the matrix to identify the rows and
        // columns containing zeros and store their indexes in the appropriate has sets
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int val = matrix[r][c];
                if(val == 0) {
                    rowSet.add(r);
                    colSet.add(c);
                }
            }
        }
        // set any cell in the matrix to zero if its row index is in rowSet
        // or its column index is in colSet
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if(rowSet.contains(r) || colSet.contains(c)) {
                    matrix[r][c] = 0;
                }
            }
        }
    }

    // time O(m*n)
    // space O(1)
    public static void makerStriping(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        // check if the first row initially contains a zero
        boolean firstRowContainsZero = false;
        for (int c = 0; c < col; c++) {
            if (matrix[0][c] == 0) {
                firstRowContainsZero = true;
                break;
            }
        }
        // check if the first column initially contains a zero
        boolean firstColContainsZero = false;
        for (int r = 0; r < row; r++) {
            if(matrix[r][0] == 0) {
                firstColContainsZero = true;
                break;
            }
        }

        // use the first row and column as markers
        // if an element in the submatrix is zero mark its corresponding row and column
        // in the first row and column as 0
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if(matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        // update the submatrix using the markers in the first row and column
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if(matrix[r][0] == 0 || matrix[0][col] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }
        // if the first row had a zero initially, set all elements in the first row to zero
        if(firstRowContainsZero) {
            for (int c = 0; c < col; c++) {
                matrix[0][c] = 0;
            }
        }
        // if the first column had a zero initially, set all elements in the first column to zero
        if(firstColContainsZero) {
            for (int r = 0; r < row; r++) {
                matrix[r][0] = 0;
            }
        }
    }
}
