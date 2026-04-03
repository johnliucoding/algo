package coding.pattern.chap15;

/**
 * @author liuxiaodong02
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        int[][] dp = new int[n][n];
        int max_len = 1;
        int start_index = 0;

        // base case: a single character is always a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        IO.println(matrixString(dp));
        // base case: a substring of length 2 is a palindrome if both characters are the same
        for (int i = 0; i < n-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = 1;
                max_len = 2;
                start_index = i;
            }
        }
        IO.println(matrixString(dp));
        // find palindromic substring of length 3 or greater
        for (int substring_len = 3; substring_len < n+1; substring_len++) {
            // iterate through each substring fo length 'substring_len'
            for (int i = 0; i < n - substring_len + 1; i++) {
                int j = i + substring_len - 1;
                // if the first and last characters are the same, and the inner substring is a palindrome
                // then current substring is palindrome
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                    max_len = substring_len;
                    start_index = i;
                }
            }
            IO.println(matrixString(dp));
        }
        return s.substring(start_index, start_index+max_len);
    }

    private static String matrixString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(j == matrix[i].length - 1) {
                    sb.append(matrix[i][j]);
                } else {
                    sb.append(matrix[i][j]).append(" ");
                }
            }

                sb.append("\n");

        }
        sb.append("-".repeat(matrix.length * 2));
        return sb.toString();
    }

    public static String longestPalindrome2(String s) {
        int n = s.length();
        int substring_start = 0;
        int maxLen = 0;

        for (int center = 0; center < n; center++) {

            int left = center; int right = center;
            while (left >= 0 && right < n &&  s.charAt(left) == s.charAt(right)) {
                if(right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    substring_start = left;
                }
                left--;
                right++;
            }
            left = center; right = center+1;
            while (left >= 0 && right < n &&  s.charAt(left) == s.charAt(right)) {
                if(right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    substring_start = left;
                }
                left--;
                right++;
            }

//            while (left > 0 && right < n - 1 &&  s.charAt(left - 1) == s.charAt(right + 1)) {
//                left--;
//                right++;
//            }
//            int length = right - left + 1;
//            if(length > maxLen) {
//                maxLen = length;
//                substring_start = left;
//            }
//
//            if (center < n - 1 && s.charAt(center) ==  s.charAt(center + 1)) {
//                left = center;
//                right = center + 1;
//                while (left > 0 && right < n - 1 &&  s.charAt(left - 1) == s.charAt(right + 1)) {
//                    left--;
//                    right++;
//                }
//                length = right - left + 1;
//                if(length > maxLen) {
//                    maxLen = length;
//                    substring_start = left;
//                }
//            }
        }
        return s.substring(substring_start, substring_start+maxLen);
    }
}
