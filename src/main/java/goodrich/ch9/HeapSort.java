package ch9;


import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2024/9/1 11:09 PM
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = { 4, 2, 1, 7, 3, 9 ,0, 5};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        // heap construction
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            sink(arr, arr.length, i);
        }

        int unsortedLength = arr.length;
        while(unsortedLength > 0) {
            // swap max to arr end
            swap(arr, 0, unsortedLength - 1);
            // heap sink inside unsorted arr
            sink(arr, --unsortedLength, 0);
        }

    }

    private static void sink(int[] arr, int arrSz, int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int maxChild = left;
            if(right < arrSz && arr[right] > arr[maxChild]) {
                maxChild = right;
            }
            if(left >= arrSz || arr[i] > arr[maxChild] ) {
                break;
            }
            swap(arr, maxChild, i);
            i = maxChild;

        }

    }

    private static void swap(int[] arr, int maxChild, int i) {
        int temp = arr[maxChild];
        arr[maxChild] = arr[i];
        arr[i] = temp;
    }
}
