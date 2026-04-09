package recursion.chap2;

public class SubStringIterative {

    public static void main(String[] args) {
        var index = findSubString("cat", "My cat Zophie");
        System.out.println(index);
    }


    public static int findSubString(String needle, String haystack) {
        var i = 0;
        while (i < haystack.length()) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}