package sort;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2025/1/12 5:09 PM
 */
public class Quicksort {
    public static  <T extends Comparable<? super T>> void sort(T[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void quicksort(T[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(arr, low, high);
        quicksort(arr, low, pivot - 1);
        quicksort(arr, pivot + 1, high);
    }

    private static <T extends Comparable<? super T>> void swap(T[] arr, int first, int second) {
        if (first != second) {
            T temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }
    }

    private static <T extends Comparable<? super T>> int partition(T[] arr, int low, int high) {
        int pivotIndex = (low + high) / 2;
        swap(arr, pivotIndex, high);
        int pivotIndexCounter = low;
        for (int i = low; i < high; i++) {
            if(arr[i].compareTo(arr[high]) <= 0 ) {
                swap(arr, pivotIndexCounter, i);
                pivotIndexCounter++;
            }
        }
        swap(arr, pivotIndexCounter, high);

        return pivotIndexCounter;
    }


}
