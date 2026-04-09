package recursion.chap5;

import java.util.Arrays;

public class SumDivConq {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        System.out.println(sum(a));
        int[] b = {5,2,4,8};
        System.out.println(sum(b));
        int[] c = {1,10,100,1000};
        System.out.println(sum(c));
    }

    public static int sum(int[] numbers) {
        if(numbers.length == 0) {
            return 0;
        } else if (numbers.length == 1) {
            return numbers[0];
        } else {
            // RECURSIVE CASE
            int mid = numbers.length / 2;
            int leftHalf = sum(Arrays.copyOfRange(numbers, 0, mid));
            int rightHalf = sum(Arrays.copyOfRange(numbers, mid, numbers.length));

            return leftHalf + rightHalf;
        }
        
    }
}
