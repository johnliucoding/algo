package coding.pattern.chap1;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class PairSumSortedTest {

    private static final Logger logger = LoggerFactory.getLogger(PairSumSortedTest.class);

    @ParameterizedTest
    @MethodSource("dataProvider")
    void twoPointer(int[] nums, int target, int[][] expected) {

        int[] result = PairSumSorted.twoPointer(nums, target);
        logger.atInfo()
                .setMessage("found indexes. result: {}")
                .addArgument(result)
                .addKeyValue("result", result).log();

        boolean contains = false;
        for (int[] exp : expected) {
            Arrays.sort(exp);
            Arrays.sort(result);
            if (Arrays.equals(exp, result)) {
                contains =true;
                break;
            }
        }
        assertTrue(contains);

    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(new int[]{}, 0, new int[][]{{}}),
                arguments(new int[]{1}, 1, new int[][]{{}}),
                arguments(new int[]{2,3}, 5, new int[][]{{0,1}}),
                arguments(new int[]{2,4}, 5, new int[][]{{}}),
                arguments(new int[]{2,2,3}, 5, new int[][]{{0,2}, {1,2}}),
                arguments(new int[]{-1,2,3}, 2, new int[][]{{0,2}}),
                arguments(new int[]{-3,-2,-1}, -5, new int[][]{{0,1}})
        );
    }
}