package coding.pattern.chap19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxCollinearPointsTest {

    @Test
    void gcd() {
        int gcd = MaxCollinearPoints.gcd(-13, -26);
        assertEquals(-13,gcd);
    }

    @Test
    void maximumCollinearPoints() {
        int[][] points = {{1,1},{1,3},{2,2},{3,1},{3,3},{4,4}};
        int result = MaxCollinearPoints.maximumCollinearPoints(points);
        assertEquals(4,result);
    }
}