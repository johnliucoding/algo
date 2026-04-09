package sort;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2025/1/12 3:49 PM
 */


public record DualPivotQuicksort<T extends Comparable<T>>(T[] arr) {
    public record Pivot(int left, int right) {
    }

    public void sort() {
        dualPivotQuicksort(0, arr.length - 1);
    }

    private void dualPivotQuicksort(int low, int high) {
        if (low >= high) {
            return;
        }
        Pivot pivot = partition(low, high);
        dualPivotQuicksort(low, pivot.left() - 1);
        dualPivotQuicksort(pivot.left() + 1, pivot.right() - 1);
        dualPivotQuicksort(pivot.right() + 1, high);
    }

    private void swap(int first, int second) {
        if (first != second) {
            T temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }
    }

    private Pivot partition(int low, int high) {
        if (arr[low].compareTo(arr[high]) > 0) {
            swap(low, high);
        }
        int leftPivotIndex = low + 1;
        int rightPivotIndex = high - 1;

        int iterator = low + 1;
        while (iterator <= rightPivotIndex) {
            if (arr[iterator].compareTo(arr[low]) < 0) {
                swap(iterator++, leftPivotIndex++);
            } else if (arr[iterator].compareTo(arr[high]) > 0) {
                swap(iterator, rightPivotIndex--);
            } else {
                iterator++;
            }
        }
        swap(low, --leftPivotIndex);
        swap(high, ++rightPivotIndex);
        return new Pivot(leftPivotIndex, rightPivotIndex);
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
