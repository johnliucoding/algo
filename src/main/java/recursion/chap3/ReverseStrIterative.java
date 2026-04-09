package recursion.chap3;

public class ReverseStrIterative {
    public static void main(String[] args) {
        System.out.println(reverse(""));
        System.out.println(reverse("X"));
        System.out.println(reverse("abcdef"));
        System.out.println(reverse("Hello, world!"));
    }

    public static String reverse(String input) {

        char[] charArr = input.toCharArray();
        int len = charArr.length;
        char[] newCharArr = new char[len];
        
        for (int i = 0; i < len; i++) {
            int idx = len - 1 - i;
            newCharArr[i] = charArr[idx];
        }

        return new String(newCharArr);
    }
}
