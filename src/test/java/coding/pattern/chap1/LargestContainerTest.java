package coding.pattern.chap1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author liuxiaodong02
 */
class LargestContainerTest {

    private static final Logger log = LoggerFactory.getLogger(LargestContainerTest.class);

    @ParameterizedTest
    @MethodSource("dataProvider")
    void pointer(int[] nums, int expected) {
        int result = LargestContainer.pointer(nums);
        log.atInfo()
                .setMessage("max container size {}")
                .addArgument(result)
                .log();
        assertEquals(expected, result);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(new int[]{}, 0),
                arguments(new int[]{1}, 0),
                arguments(new int[]{0,1,0}, 0),
                arguments(new int[]{3,3,3,3}, 9),
                arguments(new int[]{1,2,3}, 2),
                arguments(new int[]{3,2,1}, 2)
        );
    }
}