package recursion.chap2;

public class ExponentRecursion {
    public static void main(String[] args) {
        int exp = exponent(17, 10);
        System.out.println(exp);
    }

    public static int exponent(int a, int n) {
        if (n == 1) {
            // BASE CASE
            return a;
        }
        if ( n % 2 == 0) {
            // RECURSIVE CASE (when n is even.)
            var result = exponent(a, n / 2);
            return result * result;
        } else {
            // RECURSIVE CASE (when n is odd.)
            var result = exponent(a, n / 2);
            return result * result * a;
        }
    }
}
