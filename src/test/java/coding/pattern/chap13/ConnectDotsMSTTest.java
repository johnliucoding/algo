package coding.pattern.chap13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectDotsMSTTest {

    @Test
    void connect() {
        int[][] points = new int[][]{{1,1},{2,6},{3,2},{4,3},{7,1}};
        int result = ConnectDotsMST.connect(points);
        assertEquals(15, result);
    }
}