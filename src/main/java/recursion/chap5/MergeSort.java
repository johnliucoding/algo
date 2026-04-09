package recursion.chap5;

import java.util.Arrays;
import java.util.StringJoiner;

public class MergeSort {
    
    public static void main(String[] args) {
        int[] arr = { 81, 48, 94, 87, 83, 14, 6, 42 };
        int[] sorted = sort(arr, arr.length);

        System.out.println(Arrays.toString(sorted));
    }

    /**
     * get string representation of certain range of int array
     * 
     * @param arr  array to print
     * @param from start index inclusive
     * @param to   end index inclusive
     */
    public static String ars(int[] arr, int from, int to) { // the range is inclusive
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = from; i <= to; i++) {
            joiner.add(Integer.toString(arr[i]));
        }
        return joiner.toString();
    }

    public static int[] sort(int[] items, int length) { // the range is inclusive
        System.out.println(".....mergeSort() called on: " + Arrays.toString(items));

        // BASE CASE - zero or one item is naturally sorted
        if(length == 0 || length == 1) {
            // return items;

            return Arrays.copyOf(items, length); // honor the contract
        }

        // RECURSIVE CASE - pass the left and right halves to mergeSort()
        int mid = length / 2;
        System.out.println("................Split into:" + Arrays.toString(Arrays.copyOfRange(items, 0, mid)) + " and " + Arrays
                .toString(Arrays.copyOfRange(items, mid, length)));
        int[] left =
            sort(Arrays.copyOfRange(items, 0, mid), mid);
        int[] right = 
            sort(Arrays.copyOfRange(items, mid, length), length - mid);

        // BASE CASE - return merged, sorted data:
        // at this point, left and right should be sorted. we can merge them into a single sorted list
        int[] sortedResult = new int[length];
        int iLeft = 0;
        int iRight = 0;
        for (int i = 0; i < length; i++) {
            // if one of the index has reach the end its list
            // puts the rest of the other list into sortedResult
            if(iLeft == left.length) {
                System.arraycopy(right, iRight, sortedResult, i, right.length - iRight);
                break;
            }
            if(iRight == right.length) {
                System.arraycopy(left, iLeft, sortedResult, i, left.length - iLeft);
                break;
            }

            // add the smaller value to sortedResult
            if(left[iLeft] < right[iRight]) {
                sortedResult[i] = left[iLeft];
                iLeft++;
            } else {
                sortedResult[i] = right[iRight];
                iRight++;
            }
            
        }
        System.out.println("the two halves merged into: " + Arrays.toString(sortedResult));
        return sortedResult;

    }

    public static void swap(int[] items, int idxA, int idxB) {
        int temp = items[idxA];

        items[idxA] = items[idxB];
        items[idxB] = temp;
    }
}
