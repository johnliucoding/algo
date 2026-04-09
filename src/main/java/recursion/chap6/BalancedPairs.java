package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class BalancedPairs {
    public static void main(String[] args) {

        List<String> list = getBalancedParens(2);
        System.out.println(list);
        
    }

    public static List<String> getBalancedParens(int pairs) {
        return getBalancedParens(pairs, pairs, "");
        
    }

    public static List<String> getBalancedParens(int openRem, int endRem, String currStr) {

        // require openRem > 0, endRem > 0, openRem == endRem

        if(openRem == 0 && endRem == 0) {
            // BASE CASE
            return List.of(currStr);
        }
        // RECURSIVE CASE
        var list = new ArrayList<String>();
        if(openRem > 0) {
            list.addAll(getBalancedParens(openRem-1, endRem, currStr + '('));
        }
        if(endRem > openRem) {
            list.addAll(getBalancedParens(openRem, endRem-1, currStr + ")"));
        }

        // BASE CASE
        return list;
    }
}
