package coding.pattern.chap4;

import java.util.ArrayList;
import java.util.List;

public class HappyNumber {

    public static boolean test(int num) {

        var slow = num;
        var fast = num;
        while (fast!= 1) {
            slow = sumOfDigitsSquared(slow);
            fast = sumOfDigitsSquared(sumOfDigitsSquared(fast));
            if(slow == fast) {
                return false;
            }
        }

        return true;
    }

    public static int[] getDigits(int num) {
        List<Integer> digits = new ArrayList<>();
        while (num != 0) {
            int last_digit = num % 10;
            digits.add(last_digit);
            num = num / 10;
        }
        return digits.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int sumOfDigitsSquared(int num) {
        int[] digits = getDigits(num);
        int sum = 0;
        for (int digit : digits) {
            sum += digit * digit;
        }
        return sum;
    }





}
