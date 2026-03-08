package coding.pattern.chap6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstLastOccurrenceTest {

    @Test
    void indexes() {
        int[] inputs = {1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] indexes = FirstLastOccurrence.indexes(inputs, 4);
        assertArrayEquals(new int[]{3, 5}, indexes);
    }
    @Test
    void indexes2() {
        int[] inputs = {1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] indexes = FirstLastOccurrence.indexes2(inputs, 4);
        assertArrayEquals(new int[]{3, 5}, indexes);
    }

    @Test
    void indexes3() {
        int[] inputs = {1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] indexes = FirstLastOccurrence2.indexes(inputs, 4);
        assertArrayEquals(new int[]{3, 5}, indexes);
    }
}