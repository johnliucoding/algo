package coding.pattern.chap5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstringAnagramsTest {

    @Test
    void anagramCount() {
        int count = SubstringAnagrams.anagramCount("caabab", "aba");
        assertEquals(2, count);
    }

    @Test
    void anagramCount2() {
        int count = SubstringAnagrams2.anagramCount("caabab", "aba");
        assertEquals(2, count);
    }
}