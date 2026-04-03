package coding.pattern.chap15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class LongestCommonSubsequenceTest {

    @Test
    void longestCommonSubsequence() {

        int result = LongestCommonSubsequence.longestCommonSubsequence("acabac", "aebab");
        assertEquals(3, result);

        int result2 = LongestCommonSubsequence.longestCommonSubsequenceOptimized("acabac", "aebab");
        assertEquals(3, result2);
    }
}