package coding.pattern.chap19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseDigitsTest {

    @Test
    void reverseDigits() {

        assertEquals(24, ReverseDigits.reverseDigits(420));
        assertEquals(-51, ReverseDigits.reverseDigits(-15));
    }
}