package coding.pattern.chap17;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortTest {

    private static final Logger log = LoggerFactory.getLogger(ArraySortTest.class);

    @Test
    void sort() {
        Integer[] input = {6,8,4,2,7,3,1,5};
        Integer[] result = ArraySort.sort(input);
        log.atInfo().setMessage("result {}").addArgument(() -> Arrays.deepToString(result)).log();

    }
}