package recursion.chap2;

public class SubStringRecursive {
    public static void main(String[] args) {
        var index = findSubString("cat", "My cat Zophie", 0);

        System.out.println(index);
    }
    public static int findSubString(String needle, String heystack, int i) {
        if (i >= heystack.length()) {
            // BASE CASE (Needle not found.)
            return -1;
        }

        if(heystack.substring(i, i+needle.length()).equals(needle)) {
            // BASE CASE (Needle found.)
            return i;
        } else {
            // RECURSIVE CASE
            return findSubString(needle, heystack,  i + 1);
        }



    }
}
