package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class BalancedPairs {
    static void main(String[] args) {

        List<String> list = getBalancedParens(4);
        System.out.println(list);

        List<String> balancedParens2 = getBalancedParens2(4);
        System.out.println(balancedParens2);

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
            // automatic backtrack, openRem, currStr, 在调用返回后都会还原
            list.addAll(getBalancedParens(openRem-1, endRem, currStr + '('));
        }
        if(endRem > openRem) {
            list.addAll(getBalancedParens(openRem, endRem-1, currStr + ")"));
        }

        // BASE CASE
        return list;
    }
    public static List<String> getBalancedParens2(int pairs) {
        // track parameter
        ArrayList<String> strings = new ArrayList<>();
         getBalancedParens(pairs, pairs, "", strings);
        return strings;

    }
    public static void getBalancedParens(int openRem, int endRem, String currStr, List<String> result) {

        // require openRem > 0, endRem > 0, openRem == endRem

        if(openRem == 0 && endRem == 0) {
            // BASE CASE
            result.add(currStr);
            return;
        }
        // RECURSIVE CASE
        if(openRem > 0) {
            // automatic backtrack, openRem, currStr, 在调用返回后都会还原
           getBalancedParens(openRem-1, endRem, currStr + '(', result);
        }
        if(endRem > openRem) {
            getBalancedParens(openRem, endRem-1, currStr + ")", result);
        }

    }
}
