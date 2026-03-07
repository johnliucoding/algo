package coding.pattern.chap4;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HappyNumberTest {

    private static final Logger log = LoggerFactory.getLogger(HappyNumberTest.class);

    @Test
    void getDigits() {

        int num = 489;
        int[] digits = HappyNumber.getDigits(num);
        log.atInfo().setMessage("digits: {}").addArgument(digits).log();
        assertArrayEquals(new int[]{9, 8, 4}, digits);
    }

    @Test
    void happyNumberTest() {
        assertTrue(HappyNumber.test(23));
        assertFalse(HappyNumber.test(116));
    }
}