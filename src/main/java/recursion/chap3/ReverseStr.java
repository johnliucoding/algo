package recursion.chap3;

public class ReverseStr {
    public static void main(String[] args) {
        System.out.println(reverse(""));
        System.out.println(reverse("X"));
        System.out.println(reverse("abcdef"));
        System.out.println(reverse("Hello, world!"));
    }

    public static String reverse(String input) {

        if(input.length() == 0 || input.length() == 1) {
            // BASE CASE
            return input;
        } else {
            // RECURSIVE CASE
            var head = input.substring(0, 1);
            var tail = input.substring(1);
            return reverse(tail) + head;
        }
        
    }
}
