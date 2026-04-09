package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class Combination2 {
    public static void main(String[] args) {
        List<String> combList = combi("ABCD", 2);
        System.out.println(combList);
    }

    public static List<String> combi(String str, int len) {
        var list = new ArrayList<String>();
        backtrack(0, "", str, len, list);
        return list;
    }

    private static void backtrack(int start, String comb, String str, int len, List<String> res) {
        if(comb.length() == len) {
            res.add(comb);
            return;
        }
        for (int i = start; i < str.length(); i++) {
            String newComb = comb +  str.charAt(i);
            backtrack(i+1, newComb, str, len, res);
        }
    }
}
