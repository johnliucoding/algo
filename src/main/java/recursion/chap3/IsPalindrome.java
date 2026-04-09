package recursion.chap3;

public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("tacocat"));
        System.out.println(isPalindrome("zophie"));
        
    }

    public static boolean isPalindrome(String input) {
        if(input.length() == 0 || input.length() == 1) {
            // BASE CASE
            return true;
        } else {
            // RECURSIVE CASE
            var head = input.substring(0, 1);
            var middle = input.substring(1, input.length() - 1);
            var last = input.substring(input.length() - 1);

            return head.equals(last) && isPalindrome(middle); // boolean short-circuiting
        }
    }
}
