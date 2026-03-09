package coding.pattern.chap7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepeatedRemovalAdjacentDuplicatesTest {

    @Test
    void process() {
        String result1 = RepeatedRemovalAdjacentDuplicates.process("aacabba");
        assertEquals("c", result1);

        String result2 = RepeatedRemovalAdjacentDuplicates.process("aaa");
        assertEquals("a", result2);
    }
}