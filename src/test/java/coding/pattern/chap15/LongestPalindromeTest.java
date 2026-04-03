package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class LongestPalindromeTest {

    @Test
    void longestPalindrome() {
        String result = LongestPalindrome.longestPalindrome("abccbaba");
        assertEquals("abccba", result);

        String result2 = LongestPalindrome.longestPalindrome2("abccbaba");
        assertEquals("abccba", result2);
    }
}