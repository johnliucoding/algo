package coding.pattern.chap1;


public class LexicographicalSequence {

    public static String next(String input) {

        if(input.length() < 2) {
            return input;
        }
        char[] chars = input.toCharArray();
        // locate the pivot, which is the first character from the right that breaks
        // non-increasing order. start search from the second-to-last position,
        // since the last character is neither increasing nor decreasing.
        int pivot = chars.length - 2;
        while (pivot >= 0 && chars[pivot] >= chars[pivot + 1]) {
            pivot--;
        }
        // if pivot is not found, the string is already in its largest permutation.
        // in this case, reverse the string to obtain the smallest permutation
        if(pivot == -1) {
            reverse(chars, 0, chars.length - 1);
            return new String(chars);
        }

        // find the rightmost successor to the pivot
        int rightmost_successor = chars.length - 1;
        while(chars[rightmost_successor] <= chars[pivot]) {
            rightmost_successor--;
        }
        // swap the rightmost successor with the pivot to increase the lexicographical
        // order of the suffix
        swap(chars, pivot, rightmost_successor);

        // reverse the suffix after the pivot to minimize its permutation
        reverse(chars, pivot+1, chars.length -1);

        return new String(chars);
    }

    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left, right);
            left++;
            right--;
        }
    }


    private static void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }
}
