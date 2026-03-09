package coding.pattern.chap7;

import java.util.ArrayDeque;

public class ExpressionEvaluating {

    public static int eval(String str) {
        var stack = new ArrayDeque<Integer>();
        var res = 0;
        var sign = 1;
        var curr_num = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(Character.isDigit(c)) {
                curr_num = 10 * curr_num + Integer.parseInt(c + "");
            // if the current character is an operator, add 'curr_num' to the result after
            // multiplying it by its sign
            } else if (c == '-' || c == '+') {
                res += sign * curr_num;
                // update the sign and reset 'curr_num'
                curr_num = 0;
                sign = c == '-' ? -1 : 1;
            // if the current character is an opening parenthesis, a new nested
            // expression is starting
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1; // because, before '(' must be a operator, curr_num is already is 0
            } else if (c == ')') {
                res += sign * curr_num;
                curr_num = 0;
//                sign = 1;

                res *= stack.pop(); // sign before sub expression
                res += stack.pop(); // res before sub expression and operator
            }
        }
        return res  + curr_num  * sign;
    }
}
