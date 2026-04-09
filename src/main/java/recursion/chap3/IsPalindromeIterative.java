package recursion.chap3;

public class IsPalindromeIterative {
    public static void main(String[] args) {
        System.out.println(isPalindrome("tacocat"));
        System.out.println(isPalindrome("zophie"));
    }

    public static boolean isPalindrome(String input) {
        char[] charArray = input.toCharArray();
        int len = charArray.length;

        int head = 0;
        int tail = len - 1;
        while(head < tail) {
            if(charArray[head] != charArray[tail]) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
}
