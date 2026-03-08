package coding.pattern.chap5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestUniformSubstringAfterReplacementTest {

    @Test
    void get() {

        int result = LongestUniformSubstringAfterReplacement.get("aabcdcca", 2);
        assertEquals(5, result);

        int result2 = LongestUniformSubstringAfterReplacement.get("aabcdeca", 2);
        assertEquals(4, result2);
    }

    @Test
    void get2() {

        int result = LongestUniformSubstringAfterReplacement.get2("aabcdcca", 2);
        assertEquals(5, result);

        int result2 = LongestUniformSubstringAfterReplacement.get2("aabcdeca", 2);
        assertEquals(4, result2);
    }
}