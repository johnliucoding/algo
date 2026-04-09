package recursion.chap3;

public class Ackermann {
    public static void main(String[] args) {
        System.out.println("Starting with m = 1, n = 1:");
        System.out.println(ackermann(1, 1, 0));

        System.out.println("Starting with m = 2, n = 3:");
        System.out.println(ackermann(2, 3, 0));

    }

    public static int ackermann(int m, int n, int indentation) {
        String funcCall = String.format("%sackermann(%s, %s)", 
                space(indentation), 
                Integer.toString(m),
                Integer.toString(n));
        System.out.println(funcCall);

        if (m == 0) {
            // BASE CASE
            return n + 1;
        } else if (m > 0 && n == 0) {
            // RECURSIVE CASE
            return ackermann(m - 1, 1, indentation + 1);
        } else {
            // RECURSIVE CASE
            return ackermann(m - 1, ackermann(m, n - 1, indentation + 1), indentation + 1);
        }

    }

    public static String space(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
