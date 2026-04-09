package recursion.chap6;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    public static void main(String[] args) {
        var str = "ABCD";
        System.out.println(comb(str, 2));
    }

    public static List<String> comb(String chars, int len) {
        // require len <= chars.length()
        if(len == 0) {
            // 取 0 个， 只有一种情况
            return List.of("");
        } else if( chars.length() == 0) {
            // 有0个，取大于0个， 没有可能
            return List.of();
        } else if(len == chars.length()) { // 优化
            // 有n个取n个，只有一种情况
            return List.of(chars);
        }

        // RECURSIVE CASE
        var result = new ArrayList<String>();
        // FIRST: get the combos that include the head:
        var head = chars.substring(0, 1);
        var rest = chars.substring(1);
        var tailCombs = comb(rest, len-1);
        for (String tailCombString : tailCombs) {
            result.add(head + tailCombString);
        }
        // SECOND: get the combos that don't include the head:
        result.addAll(comb(rest, len));

        return result;
    }
}
