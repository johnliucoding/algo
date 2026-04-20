package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class PermutationDup {
    public static void main(String[] args) {

        // var chars = "JPB123";
        // List<String> permsList = perm(chars, 4, "");
        // System.out.println(permsList.size());
        // System.out.println(permsList);



        // System.out.println("---");
        // List<String> permWithoutDup = permWithoutDup(chars, 4, "");
        // System.out.println(permWithoutDup.size());
        // System.out.println(permWithoutDup);


        // System.out.println(permWithoutDup("ABCD", 2, ""));
        List<String> abcd = permWithoutDup("ABCD", 4, "");
        System.out.println(abcd);
// [ABCD, BACD, BCAD, BCDA, ACBD, CABD, CBAD, CBDA, ACDB, CADB, CDAB, CDBA, ABDC, BADC, BDAC, BDCA, ADBC, DABC, DBAC, DBCA, ADCB, DACB, DCAB, DCBA]
//        permWithoutDupPrint("ABCD", 2, "");
    }

    public static List<String> perm(String chars, int permLen, String prefix) {
        // require chars not null
        // require chars.length >= permLen
        
        if(permLen == 0) {
            // BASE CASE
            return List.of(prefix);
        }

        // RECURSIVE CASE
        var results = new ArrayList<String>();
        char[] charArr = chars.toCharArray();
        for (char c : charArr) {
            String newPrefix = prefix + Character.toString(c);
            List<String> other = perm(chars,  permLen - 1, newPrefix);

            results.addAll(other);
        }

        return results;
    }

    public static List<String> permWithoutDup(String chars, int permLen, String prefix) {
        // require chars not null
        // require chars.length >= permLen

        System.out.println("called with:" + chars + " permlen: " + permLen);

        if (permLen == 0) {
            // BASE CASE
            return List.of(prefix);
        }

        // RECURSIVE CASE
        var results = new ArrayList<String>();

        for (int i = 0; i < chars.length(); i++) {
            String newPrefix = prefix + chars.charAt(i);

            String newChars = chars.substring(0, i) + chars.substring(i + 1);
            List<String> other = permWithoutDup(newChars, permLen - 1, newPrefix);

            results.addAll(other);
        }

        return results;
    }

    public static void permWithoutDupPrint(String chars, int permLen, String prefix) {
        // require chars not null
        // require chars.length >= permLen

        System.out.println("called with:" + chars + " permlen: " + permLen);

        if (permLen == 0) {
            // BASE CASE
            System.out.println(prefix);
            return; // don't forget this return
        }

        // RECURSIVE CASE
        for (int i = 0; i < chars.length(); i++) {
            String newPrefix = prefix + chars.charAt(i);
            String newChars = chars.substring(0, i) + chars.substring(i + 1);
            permWithoutDupPrint(newChars, permLen - 1, newPrefix);
        }
    }
}


// printting to terminal
// collecting to List
// collecting to array, need to know the size
// generator/stream
    // can it take advantage the laziness??