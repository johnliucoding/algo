package coding.pattern.chap1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MoveZeroTest {

    private static final Logger log = LoggerFactory.getLogger(MoveZeroTest.class);

    @ParameterizedTest
    @MethodSource("dataProvider")
    void moveToEnd(int[] nums, int[] expect) {
        int[] result = MoveZero.moveToEnd(nums);
        log.atInfo().setMessage("result is {}").addArgument(result).log();
        assertArrayEquals(expect, result);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(new int[]{}, new int[]{}),
                arguments(new int[]{0}, new int[]{0}),
                arguments(new int[]{1}, new int[]{1}),
                arguments(new int[]{0, 0, 0}, new int[]{0, 0, 0}),
                arguments(new int[]{1, 3, 2}, new int[]{1, 3, 2}),
                arguments(new int[]{1, 1, 1, 0, 0}, new int[]{1, 1, 1, 0, 0}),
                arguments(new int[]{0, 0, 1, 1, 1}, new int[]{1, 1, 1, 0, 0})
        );
    }
}