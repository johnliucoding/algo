package coding.pattern.chap5;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringCount {

    public static int longestSubstringOfUniqueChars(String str) {

        int longest = 0;

        int left = 0;
        int right = 0;
        while (right < str.length()) {
            while (hasDuplicates(str, left, right)) {
                left++;
            }
            longest = Math.max(longest, right - left);
            right++;
        }
        return longest;
    }

    // time O(n)
    // space O(m)
    public static int longestSubstringOfUniqueChars2(String str) {

        HashSet<Character> window_set = new HashSet<>();

        int longest = 0;

        int left = 0;
        int right = 0;
        while (right < str.length()) {
            // if we encounter a duplicate character in the window, shrink the window until
            // it's no longer has a duplicate.
            while (window_set.contains(str.charAt(right))) {
                window_set.remove(str.charAt(left));
                left++;
            }
            // once there are no more duplicates in the window,
            // update longest
            window_set.add(str.charAt(right));
            longest = Math.max(longest, right - left + 1);
            // expand the window
            right++;
        }
        return longest;
    }

    public static int longestSubstringOfUniqueChars3(String str) {


        HashMap<Character, Integer> prev_indexes = new HashMap<>();

        int max_len = 0;

        int left = 0;
        int right = 0;
        while (right < str.length()) {
            // if a previous index of the current character is present
            // in the current window, it's a duplicate character in the window
            if (prev_indexes.containsKey(str.charAt(right))
                    && prev_indexes.get(str.charAt(right)) >= left) {
                // shrink the window to exclude the previous occurrence of this character
                left = prev_indexes.get(str.charAt(right)) + 1;
            }
            // update max_len if the current window is larger
            max_len = Math.max(max_len, right - left + 1);
            prev_indexes.put(str.charAt(right), right);
            // expand the window
            right++;
        }
        return max_len;
    }



    public static boolean hasDuplicates(String str, int start, int end) {
        HashSet<Character> set = new HashSet<>();
        for (int i = start ; i < end; i++) {
            char c = str.charAt(i);
            if (set.contains(c)) {
                return true;
            }
            set.add(c);
        }
        return false;
    }
}
