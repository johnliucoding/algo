package coding.pattern.chap18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapOddAndEvenTest {

    @Test
    void swap() {
        assertEquals(43, SwapOddAndEven.swap(23));
    }
}