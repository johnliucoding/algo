package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixInfectionTest {

    @Test
    void infect() {
        int[][] matrix = {{1,1,1,0}, {0,0,2,1},{0,1,1,0}};
        int steps = MatrixInfection.infect(matrix);
        assertEquals(3, steps);
    }
}