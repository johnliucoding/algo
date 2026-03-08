package coding.pattern.chap5;

import java.util.HashMap;
import java.util.Objects;

public class LongestUniformSubstringAfterReplacement {

    public static int get(String str, int k) {
        Objects.requireNonNull(str);
        int max_len = 0;

        int left = 0;
        int right = 0;
        while (right < str.length()) {
            while (right - left + 1 - highest_freq(str, left, right) > k) {
                left++;
            }
            max_len = Math.max(max_len, right - left + 1);
            right++;
        }
        return max_len;
    }

    public static int highest_freq(String str, int start, int end) {
        int highest_freq = 0;
        var freq_map = new HashMap<Character, Integer>();
        for (int i = start; i <= end; i++) {
           char c = str.charAt(i);
           highest_freq = Math.max(highest_freq, freq_map.merge(c, 1, Integer::sum));
        }
        return highest_freq;
    }

    public static int get2(String str, int k) {
        Objects.requireNonNull(str);

        int max_len = 0;
        int history_highest_freq = 0; // not current window's highest freq
        var window_freq_map = new HashMap<Character, Integer>();

        int left = 0;
        int right = 0;
        while (right < str.length()) {
            // update the frequency of the character at the right pointer
            // and the highest frequency for the current window
            int freq = window_freq_map.merge(str.charAt(right), 1, Integer::sum);
            history_highest_freq = Math.max(history_highest_freq, freq);

            // calculate replacements needed for the current window
            int num_need_to_replace = right - left + 1 - history_highest_freq;

            // slide the window if the number of replacements needs exceeds
            // 'k', the right pointer always gets advanced, so we just need to advance 'left'
            if(num_need_to_replace > k) {
                // (set to 0) remove the character at the left pointer from the hash map
                // before advancing the left pointer.
                window_freq_map.merge(str.charAt(left), -1, Integer::sum);
                left++;
            }
            // since the length of the current window increases or stays the same,
            // assign the length of the current window to 'max_len'
            max_len = right - left + 1;
            // expand the window
            right++;
        }
        return max_len;
    }
}
