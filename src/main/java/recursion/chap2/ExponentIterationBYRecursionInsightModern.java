package recursion.chap2;

import java.util.ArrayDeque;

public class ExponentIterationBYRecursionInsightModern {
  
    public static void main(String[] args) {
        int exp = exponent(17, 10);
        System.out.println(exp);
    }

    static enum OP {SQUARE, MULTIPLY}

    public static int exponent(int a, int n) {
        // step 1. determine the operation to be performed
        var opStack = new ArrayDeque<OP>();
        while (n > 1) {
            if (n % 2 == 0) {
                // n is even.
                opStack.push(OP.SQUARE);
                n = n / 2;
            } else {
                // n is odd.
                opStack.push(OP.MULTIPLY);
                n--;
            }
        }
        // step 2. perform the operation in reverse order.
        var result = a; // start result at `a`
        while (!opStack.isEmpty()) {
            var op = opStack.pop();
            result = switch(op) {
                case MULTIPLY -> result * a;
                case SQUARE -> result * result;
            };
        }
        return result;
    }
}
