package coding.pattern.chap5;

import org.slf4j.spi.LoggingEventBuilder;

import java.util.Arrays;
import java.util.Objects;

public class SubstringAnagrams2 {

    public static int anagramCount(String a, String b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        if(a.length() < b.length()) {
            throw new IllegalArgumentException("a should not short than b");
        }

        // array as lookup table
        int[] expected_freq = new int[26];
        for (int i = 0; i < b.length(); i++) {
            int idx = b.charAt(i) - 'a';
            expected_freq[idx]++;
        }

        int[] window_freq = new int[26];
        int count = 0;

        int left = 0;
        int right =0;
        while (right < a.length()) {
            while (right - left  < b.length()) {
                window_freq[a.charAt(right) - 'a']++;
                right++;
            }
            if(Arrays.equals(expected_freq, window_freq)) {
                count++;
            }
            window_freq[a.charAt(left) - 'a']--;
            left++;
        }

        return count;

    }
}
