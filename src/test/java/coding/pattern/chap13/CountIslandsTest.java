package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountIslandsTest {

    @Test
    void countIslands() {
        int[][] matrix = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 0, 1}};
        int result = CountIslands.countIslands(matrix);
        assertEquals(2, result);
    }
}