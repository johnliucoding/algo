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

class LexicographicalSequenceTest {

    private static final Logger log = LoggerFactory.getLogger(LexicographicalSequenceTest.class);

    @ParameterizedTest
    @MethodSource("dataProvider")
    void next(String input, String expect) {
        String result = LexicographicalSequence.next(input);
        log.atInfo().setMessage("result: {}").addArgument(result).log();
        assertEquals(expect, result);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("a", "a"),
                arguments("aaaa", "aaaa"),
                arguments("ynitsed", "ynsdeit")
        );
    }
}