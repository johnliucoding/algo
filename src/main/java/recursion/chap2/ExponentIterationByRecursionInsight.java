package recursion.chap2;

import java.util.ArrayDeque;


public class ExponentIterationByRecursionInsight {
    
    public static void main(String[] args) {
        int exp = exponent(17, 10);
        System.out.println(exp);
    }


    public static int exponent(int a, int n) {
        // step 1. determine the operation to be performed
        var opStack =  new ArrayDeque<String>();
        while (n > 1) {
            if (n % 2 == 0) {
                // n is even.
                opStack.push("square");
                n = n / 2;
            } else {
                // n is odd.
                n--;
                opStack.push("multiply");
            }
        }
        // step 2. perform the operation in reverse order.
        var result = a; // start result at `a`
        while (!opStack.isEmpty()) {
            var op = opStack.pop();
            if(op.equals("multiply")) {
                result *= a;
            } else {
                result *= result;
            }
        }
        return result;
    }
}

// enum + switch expression