package recursion.chap2;

public class SubStringIterative {


    public static int findSubString(String needle, String haystack) {
        if(needle.length() > haystack.length()) {
            return -1;
        }

        var i = 0;
        while (i <= (haystack.length() -needle.length())) {
            if (haystack.startsWith(needle, i)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}