package coding.pattern.chap16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JumpToEndTest {

    @Test
    void canJumpToEnd() {

        int[] input1 = {3,2,0,2,5};
        int[] input2 = {2,1,0,3};
        assertTrue(JumpToEnd.canJumpToEnd(input1));
        assertFalse(JumpToEnd.canJumpToEnd(input2));

    }
}