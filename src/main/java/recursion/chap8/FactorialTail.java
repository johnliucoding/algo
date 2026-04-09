package recursion.chap8;

public class FactorialTail {
    public static void main(String[] args) {
        
    }

    public static int factorial(int number) {
        if (number == 1) {
            return 1;
        } else {
            // multiplication happens after the recursive calls are made
            return number * factorial(number - 1);
        }
    }

    public static int factorialTail(int number) {
        return factorialTailHelper(number, 1);
    }
    
    private static int factorialTailHelper(int number, int accum) {
        if (number == 1) {
            return accum;
        } else {
            // to allow the interpreter or compiler to implement tail call optimization
            // the last action a recursive function makes must be to return the results of the recursive call.
            return factorialTailHelper(number - 1, accum * number);
        }
    }
}
