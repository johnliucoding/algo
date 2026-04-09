package recursion.chap5;


import java.util.StringJoiner;

// quicksort works fastest when the partitions are evenly balanced, so selecting a good
// pivot value at each partition step is important


public class QuickSort {
    
    public static void main(String[] args) {
        int[] arr = {81, 48, 94, 87, 83, 14, 6, 42};
        sort(arr, 0, arr.length - 1);

        System.out.println(ars(arr, 0, arr.length -1 ));
    }

    /**
     * get string representation of certain range of int array 
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

    public static void sort(int[] items, int leftIdx, int rightIdx) { // the range is inclusive

        if(leftIdx >= rightIdx) {
            // with only zero or one item, `items` is already sorted.
            return; // BASE CASE
        }
        // start of the partitioning
        int locationToSwap = leftIdx;
        int pivotValue = items[rightIdx]; // select the last value for the pivot.

        // iterate up to, but not including, the pivot:
        for (int i = leftIdx; i < rightIdx; i++) {
            // if a value if less than the pivot, swap it so that it's on the left side of `items`
            if(items[i] <= pivotValue) {
                // swap small element to left
                swap(items, i, locationToSwap);
                // increment left index
                locationToSwap++;
            }
        }

        // swap the pivot to the position
        swap(items, locationToSwap, rightIdx);
        // end of the partitioning
        System.out.println("partitioned array is: ");
        System.out.println(ars(items, leftIdx, rightIdx));

        // call quicksort on the two partitions:
        sort(items, leftIdx, locationToSwap - 1); // RECURSIVE CASE
        sort(items, locationToSwap + 1, rightIdx); // RECURSIVE CASE
    }

    public static void swap(int[] items, int idxA, int idxB) {
        int temp = items[idxA];

        items[idxA] = items[idxB];
        items[idxB] = temp;
    }
}
