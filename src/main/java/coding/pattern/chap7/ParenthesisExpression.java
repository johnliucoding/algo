package coding.pattern.chap7;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * @author liuxiaodong02
 */
public class ParenthesisExpression {

    // time O(n)
    // space O(n) stack + O(1) map
    public static boolean isValid(String expression) {

        var  charMap = new HashMap<Character, Character>();
        charMap.put('(', ')');
        charMap.put('[', ']');
        charMap.put('{', '}');

        var stack = new ArrayDeque<Character>();
        for (int i = 0; i < expression.length(); i++) {
            char curr = expression.charAt(i);
            if(charMap.containsKey(curr)) {
                // push
                stack.push(curr);
            } else {
                // pop and check
                if(stack.isEmpty()) {
                    return false; // ⚠️ 这种情况
                }
                char popped = stack.pop();
                char translated = charMap.get(popped);
                if(translated != curr) {
                    return false;
                }
            }
        }
        // ⚠️ if the stack is empty, all opening parentheses were successfully closed
        return stack.isEmpty();
    }
}
