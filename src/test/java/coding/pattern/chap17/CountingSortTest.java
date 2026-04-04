package coding.pattern.chap17;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CountingSortTest {
    private static final Logger log = LoggerFactory.getLogger(CountingSortTest.class);

    @Test
    void sort() {
        int[] input = {6,8,4,2,7,3,1,5};
        int[] result = CountingSort.sort(input);
        log.atInfo().setMessage("result {}").addArgument(result).log();
    }
}