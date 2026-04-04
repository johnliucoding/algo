package coding.pattern.chap17;

import java.util.concurrent.ThreadLocalRandom;
// time O(n)
// space O(log(n))
public class KthLargestElementQuickSelect {
    public static int kthLargest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length-1, k);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if(left >= right) return arr[left];

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int random_index = random.nextInt(left, right + 1);
        int temp = arr[random_index];
        arr[random_index] = arr[right];
        arr[right] = temp;

        int pivot_index = partition(arr, left, right);
        // if the pivot comes before 'length - k' the kth largest integer is somewhere
        // to pivot's right
        if(pivot_index < arr.length - k) {
            return quickSelect(arr, pivot_index + 1, right, k);
        // if the pivot comes after 'length - k' the kth largest integer is somewhere to its left.
        } else if(pivot_index > arr.length - k) {
            return quickSelect(arr, left, pivot_index - 1, k);
        // if the pivot is at index 'length - k', it's kth largest integer is at pivot
        } else {
            return arr[pivot_index];
        }

    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int low = left;
        for(int i = left; i < right; i++) {
            if(arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[low];
                arr[low] = temp;
                low++;
            }
        }
        arr[right] = arr[low];
        arr[low] = pivot;
        return low;
    }
}
