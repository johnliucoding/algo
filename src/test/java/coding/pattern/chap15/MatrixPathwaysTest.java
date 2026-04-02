package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class MatrixPathwaysTest {

    @Test
    void matrixPathways() {
        int result1 = MatrixPathways.matrixPathways(3, 3);
        int result2 = MatrixPathways.matrixPathwaysOptimized(3, 3);

        assertEquals(result1, result2);
        assertEquals(6, result1);
    }
}