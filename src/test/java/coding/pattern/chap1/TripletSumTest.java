package coding.pattern.chap1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author liuxiaodong02
 * @date 2026/3/4 14:55
 */
class TripletSumTest {
    private static final Logger logger = LoggerFactory.getLogger(TripletSumTest.class);

    @ParameterizedTest
    @MethodSource("dataProvider")
    void twoPointer(int[] nums, int[]expected) {

        List<int[]> result = TripletSum.tripletSum(nums);
        logger.atInfo()
                .setMessage("found triplets. result: {}")
                .addArgument(() -> result.stream()
                        .map(Arrays::toString)
                        .collect(Collectors.joining(", ", "[", "]")))
                //.addKeyValue("result", result)
                .log();

        if(result.isEmpty()) {
            assertEquals(0, expected.length);
        } else {
            int[] re = result.getFirst();
            Arrays.sort(re);
            Arrays.sort(expected);
            assertArrayEquals(expected, re);
        }
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(new int[]{}, new int[]{}),
                arguments(new int[]{0}, new int[]{}),
                arguments(new int[]{1,-1}, new int[]{}),
                arguments(new int[]{0,0,0}, new int[]{0,0,0}),
                arguments(new int[]{1,0,1}, new int[]{}),
                arguments(new int[]{0,0,1,-1,1,-1}, new int[]{-1, 0, 1})
        );
    }

}