package coding.pattern.chap6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixSearchTest {

    @Test
    void search() {
        int target = 21;
        int[][] matrix = {{2, 3, 4, 6}, {7, 10, 11, 17}, {20, 21, 24, 33}};
        int[] search = MatrixSearch.search(matrix, target);
        assertArrayEquals(new int[]{2, 1}, search);
    }
}