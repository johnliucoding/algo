package sort;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2025/1/14 10:46 PM
 */
public record HeapSort<T extends Comparable<T>>(T[] arr) {

    public void sort() {
        buildHeap(arr);

        for (int swapToPos = arr.length - 1; swapToPos > 0 ; swapToPos--) {
            swap(0, swapToPos);
            heapify(arr, swapToPos, 0);

        }
    }

    private void buildHeap(T[] arr) {
        // last parent node
        int lastParentNode = arr.length / 2 - 1;
        // heapify from here on backwards
        for (int i = lastParentNode; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
    }
    private void heapify(T[] arr, int length, int parentPos) {
        while (true) {
            int left = parentPos * 2 + 1;
            int right = parentPos * 2 + 2;

            // find teh largest element
            int largestPos = parentPos;
            if(left < length && arr[left].compareTo(arr[largestPos]) > 0) {
                largestPos = left;
            }
            if(right < length && arr[right].compareTo(arr[largestPos]) > 0) {
                largestPos = right;
            }

            // if it's parent, we're done
            if(largestPos == parentPos) {
                break;
            }
            // if it's not the parent, then swap
            swap(parentPos, largestPos);
            // and fix again starting at the child we moved the parent to
            parentPos = largestPos;

        }
    }
    private void swap(int first, int second) {
        if (first != second) {
            T temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
        }
    }

    //[10, 55, -5, 34, 7, 22, 19] | [Void Elf, Vulpera, Human, Trool, Undead]
    //[-5, 7, 10, 19, 22, 34, 55] | [Human, Trool, Undead, Void Elf, Vulpera]
    public static void main(String[] args) {
        Integer[] integers = {10, 55, -5, 34, 7, 22, 19};
        String[] strings = {"Void Elf", "Vulpera", "Human", "Trool", "Undead"};
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
        new DualPivotQuicksort<>(integers).sort();
        new DualPivotQuicksort<>(strings).sort();
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
    }
}
