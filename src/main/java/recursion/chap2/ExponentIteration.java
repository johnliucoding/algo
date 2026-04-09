package recursion.chap2;

public class ExponentIteration {
    public static void main(String[] args) {
        int exp = exponent(17, 10);
        System.out.println(exp);
    }

    public static int exponent(int a, int n) {
        var result = 1;
        for (int i = 0; i < n; i++) {
            result *= a;
        }

        return result;
    }
}
