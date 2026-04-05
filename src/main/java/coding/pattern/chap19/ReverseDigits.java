package coding.pattern.chap19;

public class ReverseDigits {

    public static int reverseDigits(int num) {
        int reversed_n = 0;

        while (num != 0) {

            int digit =  num % 10;
            num = num / 10;
            // check for integer overflow or underflow
            if(reversed_n > Integer.MAX_VALUE/10 || reversed_n < Integer.MIN_VALUE/10){
                return 0;
            }
            // add the current digit to 'reversed_n'
            reversed_n = reversed_n * 10 + digit;
        }
        return reversed_n;
    }
}
