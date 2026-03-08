package coding.pattern.chap5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggingEventBuilder;

import java.util.Arrays;
import java.util.Objects;

public class SubstringAnagrams {


    private static final Logger log = LoggerFactory.getLogger(SubstringAnagrams.class);

    public static boolean isAnagram(char[] a, char[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    public static int anagramCount(String a, String b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        if(a.length() < b.length()) {
            throw new IllegalArgumentException("a should not short than b");
        }


        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int left = 0;
        int right = 0;
        int anagram_count = 0;
        while ( right < aChars.length) {
            while (right - left < bChars.length) {
                right++;
            }
            char[] sub_arr = Arrays.copyOfRange(aChars, left, right);
            LoggingEventBuilder event = log.atInfo().setMessage("substr: {}, is anagram: {}").addArgument(new String(sub_arr));
            if(isAnagram(sub_arr, bChars)) {
                event.addArgument(true).log();
                anagram_count++;
            }
            left++;
        }
        return anagram_count;
    }
}
