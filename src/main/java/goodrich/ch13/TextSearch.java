package goodrich.ch13;

import java.util.HashMap;

/**
 * @author Liu Xiaodong
 * @since 2025/1/20 10:50 AM
 */
public class TextSearch {

    /**
     * Returns the lowest index at which substring pattern begins in text(or else -1)
     *
     * @param text    to search in
     * @param pattern to search for
     * @return lowest index at which substring pattern begins in text(or else -1)
     */
    // O(nm)
    public static int findBrute(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        for (int i = 0; i < n - m; i++) {
            int k = 0;
            while (k < m && text[i + k] == pattern[k]) {
                k++;
            }
            if (k == m) {
                return i;
            }
        }
        return -1;
    }


    public static int findBoyerMoore(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;

        // trivial search for empty string
        if (m == 0) return 0;

        // 'last' map; -1 as default for character not in pattern
        final HashMap<Character, Integer> last = new HashMap<>();
        for (int k = 0; k < m; k++) {
            // rightmost occurrence in pattern is last
            last.put(pattern[k], k);
        }

        // start with the end the pattern aligned at index m - 1 of the text
        int i = m - 1; // an index into the text
        int k = m - 1; // an index into the pattern

        while (i < n) {
            if (text[i] == pattern[k]) { // a matching character
                if (k == 0) { // entire pattern has been found
                    return i;
                }
                i--;
                k--;
            } else {
                final int lastOccurs = last.get(text[i]) == null ? -1 : last.get(text[i]);
                i += m - Math.min(k, 1 + lastOccurs);
                k = m - 1;
            }
        }
        return -1;
    }

    private static int[] computeFail(char[] pattern) {
        int m = pattern.length;
        int[] fail = new int[m];
        int j = 1;
        int k = 0;
        while (j < m) {
            if (pattern[j] == pattern[k]) {
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = 0;
            } else {
                j++;
            }
        }
        return fail;
    }

    public static int findKMP(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0;

        int[] fail = computeFail(pattern);

        int j = 0;
        int k = 0;
        while (j < n) {
            if (text[j] == pattern[k]) {
                if (k == m - 1) return j - m + 1;
                j++;
                k++;
            } else if (k > 0) {
                k = fail[k - 1];
            } else {
                j++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String text = "abacaabadcabacabaabb";
        String pattern = "abacab";

        final int brute = findBrute(text.toCharArray(), pattern.toCharArray());
        final int boyerMoore = findBoyerMoore(text.toCharArray(), pattern.toCharArray());
        final int kmp = findKMP(text.toCharArray(), pattern.toCharArray());
        System.out.println(brute + ", " + boyerMoore + ", " + kmp);

    }
}
