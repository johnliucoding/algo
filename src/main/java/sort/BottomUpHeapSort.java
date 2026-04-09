package sort;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2025/1/14 11:12 PM
 */
public record BottomUpHeapSort<T extends Comparable<T>>(T[] arr) {
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
    private void heapify(T[] arr, int length, int rootPos) {
       int leafPos = findLeaf(arr, length, rootPos);
       int nodePos = findTargetNodeBottomUp(arr, rootPos, leafPos);
       if(rootPos == nodePos) {
           return;
       }
       // move all elements starting at nodePos to parent, move root to nodePos
       T nodeValue = arr[nodePos];
       arr[nodePos] = arr[rootPos];
       while (nodePos > rootPos) {
           int parentPos = getParentPos(nodePos);
           T parentValue = arr[parentPos];
           arr[parentPos] = nodeValue;
           nodePos = parentPos;
           nodeValue = parentValue;

       }
    }

    int findLeaf(T[] heap, int length, int rootPos) {
        int pos = rootPos;
        int left  = pos * 2 + 1;
        int right = pos * 2 + 2;

        while (right < length) {
            if(heap[right].compareTo(heap[left]) > 0) {
                pos = right;
            } else {
                pos = left;
            }
            left  = pos * 2 + 1;
            right = pos * 2 + 2;
        }
        if(left < length) {
            pos = left;
        }
        return pos;
    }

    int findTargetNodeBottomUp(T[] heap, int rootPos, int leafPos) {
        T parent = heap[rootPos];
        while (leafPos != rootPos && heap[leafPos].compareTo(parent) < 0) {
            leafPos = getParentPos(leafPos);
        }
        return leafPos;

    }
    int getParentPos(int pos) {
        return (pos - 1) / 2;
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
