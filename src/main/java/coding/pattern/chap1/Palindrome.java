package coding.pattern.chap1;

import java.util.Objects;

/**
 * @author liuxiaodong02
 * @date 2026/3/4 15:07
 */
public class Palindrome {

    // time O(n)
    // space O(1)
    public static boolean isPalindrome(String str) {
        Objects.requireNonNull(str, "str must not be null");
        if(str.length() < 2) {
            return true;
        }
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            // skip non-alphanumeric characters from the left
           while(left < right && !isalnum(str.charAt(left))) {
               left++;
           }
           // skip non-alphanumeric characters from the right
           while(left < right && !isalnum(str.charAt(right))) {
               right--;
           }
           // if the characters at the left and right pointers don't match
            // the string is not a palindrome
           if(str.charAt(left) != str.charAt(right)) {
               return false;
           } else {
               left++;
               right--;
           }

        }
        return true;
    }

    private static boolean isalnum(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }
}
