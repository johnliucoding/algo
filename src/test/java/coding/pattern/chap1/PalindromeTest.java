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

/**
 * @author liuxiaodong02
 */
class PalindromeTest {

    private static final Logger log = LoggerFactory.getLogger(PalindromeTest.class);

    @ParameterizedTest
    @MethodSource("dataProvider")
    void isPalindrome(String str, boolean expected) {
        boolean result = Palindrome.isPalindrome(str);
        log.atInfo()
                .setMessage("isPalindrome(str) = {}, result = {}")
                .addArgument(str)
                .addArgument(result)
                .log();
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("dataProvider2")
    void isPalindrome2(String str, boolean expected) {
        boolean result = StringCodePoints.isPalindrome(str);
        log.atInfo()
                .setMessage("isPalindrome(str) = {}, result = {}")
                .addArgument(str)
                .addArgument(result)
                .log();
        assertEquals(expected, result);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("", true),
                arguments("a", true),
                arguments("aa", true),
                arguments("ab", false),
                arguments("!, (?)", true),
                arguments("12.02.2021", true),
                arguments("21.02.2021", false),
                arguments("hello world!", false)
        );
    }

    static Stream<Arguments> dataProvider2() {
        return Stream.of(
                arguments("", true),
                arguments("a", true),
                arguments("aa", true),
                arguments("ab", false),
                arguments("!, (?)", true),
                arguments("12.02.2021", true),
                arguments("21.02.2021", false),
                arguments("hello world!", false),
                arguments("A😀B🎉C🎉B😀A", true)
        );
    }
}