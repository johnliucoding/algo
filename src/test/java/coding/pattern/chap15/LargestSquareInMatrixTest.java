package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class LargestSquareInMatrixTest {

    @Test
    void largestSquare() {
        int[][] matrix = {{1,0,1,1,0}, {0,0,1,1,1}, {1,1,1,1,0}, {1,1,1,0,1}, {1,1,1,0,1}};
        assertEquals(9, LargestSquareInMatrix.largestSquare(matrix));

        assertEquals(9, LargestSquareInMatrix.largestSquareOptimized(matrix));
    }
}