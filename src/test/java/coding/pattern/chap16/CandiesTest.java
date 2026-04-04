package coding.pattern.chap16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandiesTest {

    @Test
    void distributeCandies() {
        int[] ratings1 = {4,3,2,4,5,1};
        int[] ratings2 = {1,3,3};
        assertEquals(12, Candies.distributeCandies(ratings1));
        assertEquals(4, Candies.distributeCandies(ratings2));
    }
}