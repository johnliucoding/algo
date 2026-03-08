package coding.pattern.chap6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionIndexTest {

    @Test
    void test() {

        int result1 = InsertionIndex.insertionIndex(new int[]{1, 2, 4, 5, 7, 8, 9}, 4);
        assertEquals(2, result1);

        int result2 = InsertionIndex.insertionIndex(new int[]{1, 2, 4, 5, 7, 8, 9}, 6);
        assertEquals(4, result2);

        int result3 = InsertionIndex.insertionIndex(new int[]{1, 2, 4, 5, 7, 8, 9}, 10);
        assertEquals(7, result3);
    }
}