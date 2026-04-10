package sort;

import java.util.Arrays;

public class InsertionSort {
    // considering one element at a time,
    // placing the element in the correct order relative to those before it
    // maintaining the sorted front part

    // aka. insertion in a sorted array

    public static void main(String[] args) {
        var data = new Character[] {'r', 'a', 'c', 'b'};
        insertionSort(data);
        System.out.println(Arrays.toString(data));
    }


    public static <T extends Comparable<T>> void insertionSort(T[] data) {
        // position 0  as already sorted
        for (var i = 1; i< data.length; i++) {
            var current = data[i];
            int j = i;
            // shift to find position for 'current' value
            while (j > 0 && data[j-1].compareTo(current) > 0) {
                data[j] = data[j-1];
                j--;
            }
            data[j] = current;
        }
    }


}
