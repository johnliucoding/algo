package sort;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2025/1/12 5:09 PM
 */
public record Quicksort<T extends Comparable<T>>(T[] arr) {
    public void sort() {
        quicksort(0, arr.length - 1);
    }

    private void quicksort(int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(low, high);
        quicksort(low, pivot - 1);
        quicksort(pivot + 1, high);
    }

    private void swap(int first, int second) {
        if (first != second) {
            T temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }
    }

    private int partition(int low, int high) {
        int pivotIndex = (low + high) / 2;
        swap(pivotIndex, high);
        int pivotIndexCounter = low;
        for (int i = low; i < high; i++) {
            if(arr[i].compareTo(arr[high]) <= 0 ) {
                swap(pivotIndexCounter, i);
                pivotIndexCounter++;
            }
        }
        swap(pivotIndexCounter, high);

        return pivotIndexCounter;
    }

    public static void main(String[] args) {
        Integer[] integers = {10, 55, -5, 34, 7, 22, 19};
        String[] strings = {"Void Elf", "Vulpera", "Human", "Trool", "Undead"};
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
        new DualPivotQuicksort<>(integers).sort();
        new DualPivotQuicksort<>(strings).sort();
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
    }
}
