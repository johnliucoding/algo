package recursion.chap2;

public class SubStringRecursive {

    public static int findSubString(String needle, String haystack) {
        if(needle.length() > haystack.length()) {
            return -1;
        }
        return findSubString(needle, haystack, 0);
    }
    private static int findSubString(String needle, String haystack, int i) {
        int remainingLength = haystack.length() - i;
        if(remainingLength < needle.length()) {
            // BASE CASE (Needle not found.)
            return -1;
        }
        if(haystack.startsWith(needle, i)) {
            // BASE CASE (Needle found.)
            return i;
        } else {
            // RECURSIVE CASE
            return findSubString(needle, haystack,  i + 1);
        }
    }
}
