package coding.pattern.chap7;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class RepeatedRemovalAdjacentDuplicates {

    public static String process(String str) {
        var stack = new ArrayDeque<Character>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // if the current character is the same as the top character on the stack,
            // a pair of adjacent duplicates has been formed. So, pop the top character
            // from the stack
            if(!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                // otherwise, push the current character onto the stack
                stack.push(c);
            }

        }
// return the remaining characters as a string
        return stack.stream().map(c -> c + "").collect(Collectors.joining());

    }
}
