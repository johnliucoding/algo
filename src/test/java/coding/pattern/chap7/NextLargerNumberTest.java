package coding.pattern.chap7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author liuxiaodong02
 */
class NextLargerNumberTest {
    static Stream<Arguments> dataProvider() {
        return Stream.of(
            arguments(new int[]{5, 2, 4, 6, 1},new int[]{6, 4, 6, -1, -1})
        );
    }
    @ParameterizedTest
    @MethodSource("dataProvider")
    void find(int[] inputs, int[] expected) {
        assertArrayEquals(expected, NextLargerNumber.find(inputs));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void find2(int[] inputs, int[] expected) {
        assertArrayEquals(expected, NextLargerNumber.find2(inputs));
    }
}