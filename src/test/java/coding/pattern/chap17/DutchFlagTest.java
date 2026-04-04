package coding.pattern.chap17;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DutchFlagTest {

    private static final Logger log = LoggerFactory.getLogger(DutchFlagTest.class);

    @Test
    void sort() {
        int[] arr = new int[] {1, 2, 0, 0, 2, 1, 0, 2, 1};
        DutchFlag.sort(arr);

        log.atInfo().log(Arrays.toString(arr));
    }
}