package recursion.chap8;

public class ReverseStrTail {
    public static void main(String[] args) {
        System.out.println(reverse("", ""));
        System.out.println(reverse("X", ""));
        System.out.println(reverse("abcdef", ""));
        System.out.println(reverse("Hello, world!", ""));
    }

    public static String reverse(String input, String accum) {

        if (input.length() == 0) {
            // BASE CASE
            return accum;
        } else {
            // RECURSIVE CASE
            var head = input.substring(0, 1);
            var tail = input.substring(1);
            return reverse(tail, head+accum);
        }

    }
}
