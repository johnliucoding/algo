package coding.pattern.chap13;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class LongestIncreasingPathTest {

    private static final Logger log = LoggerFactory.getLogger(LongestIncreasingPathTest.class);

    @Test
    void longestIncreasingPath() {
        int[][] matrix = {{1,5,8}, {3, 4, 4}, {7, 9, 2}};
        int result = LongestIncreasingPath.longestIncreasingPath(matrix);
        assertEquals(5, result);
    }
}