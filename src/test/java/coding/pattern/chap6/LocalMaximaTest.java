package coding.pattern.chap6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalMaximaTest {

    private static final Logger log = LoggerFactory.getLogger(LocalMaximaTest.class);

    @Test
    void localMaxima() {

        int[] nums = new int[]{1, 4, 3, 2, 3};
        int result = LocalMaxima.localMaxima(nums);
        log.atInfo().setMessage("found index: {}").addArgument(result).log();
        assertTrue(List.of(1, 4).contains(result));

    }
}