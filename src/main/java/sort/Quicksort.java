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

    private static <T> void swap(T[] arr, int first, int second) {
        if (first != second) {
            T temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }
    }

    private static <T extends Comparable<? super T>> int partition(T[] arr, int low, int high) {
        int pivotIndex = (low + high) / 2; // !import pivot selection
        // move pivot to high
        swap(arr, pivotIndex, high);
        int left = low;
        for (int i = low; i < high; i++) { // 处理pivot左边的元素
            if(arr[i].compareTo(arr[high]) <= 0 ) { // move all element smaller than pivot to left
                swap(arr, left, i);
                left++;
            }
        }
        swap(arr, left, high);

        return left;
    }


}
