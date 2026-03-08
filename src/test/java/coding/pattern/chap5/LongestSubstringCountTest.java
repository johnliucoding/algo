package coding.pattern.chap5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringCountTest {

    @Test
    void longestSubstringOfUniqueChars1() {

        int result = LongestSubstringCount.longestSubstringOfUniqueChars("abcba");
        assertEquals(3, result);

    }
    @Test
    void longestSubstringOfUniqueChars2() {


        int result2 = LongestSubstringCount.longestSubstringOfUniqueChars2("abcba");
        assertEquals(3, result2);
    }
    @Test
    void longestSubstringOfUniqueChars3() {

        int result = LongestSubstringCount.longestSubstringOfUniqueChars3("abcba");
        assertEquals(3, result);

    }
}