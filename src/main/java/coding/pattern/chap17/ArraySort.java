package coding.pattern.chap17;


import java.util.concurrent.ThreadLocalRandom;

public class ArraySort {

    public static <T extends Comparable<T>> T[] sort(T[] input) {
        quicksort(input, 0, input.length - 1);
        return input;
    }
    private static <T extends Comparable<T>> void quicksort(T[] input, int low, int high) {
        // base case: if the subarray has 0 or 1 element, it's already sorted
        if (low >= high) {
            return;
        }
        // choose a pivot at a random index.
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int random_index = random.nextInt(low, high + 1);

        // swap the randomly chosen pivot with the rightmost element to position
        // the pivot at the rightmost index
        T temp = input[random_index];
        input[random_index] = input[high];
        input[high] = temp;

        // partition the array and retrieve the pivot index
        int pivot_index = partition(input, low, high);
        // call quicksort on the left and right parts to recursively sort them.
        quicksort(input, low, pivot_index - 1);
        quicksort(input, pivot_index + 1, high);
    }

    private static <T extends Comparable<T>> int partition(T[] input, int low, int high) {
        T pivot = input[high];
        int left =  low;

        // move all numbers less than the pivot to the left,
        // which consequently positions all numbers
        // greater than or equal to the pivot to the right
        for (int i = low; i < high; i++) {
            if(input[i].compareTo(pivot) < 0) {
                T temp = input[i];
                input[i] = input[left];
                input[left] = temp;
                left++;
            }
        }
        // after partitioning, 'left' will be positioned where the pivot should be
        // so, swap the pivot number with the number at the 'left' pointer
        input[high] = input[left];
        input[left] = pivot;
        return left;
    }

}
