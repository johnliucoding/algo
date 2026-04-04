package coding.pattern.chap17;



public class DutchFlag {

    public static int[] sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int i = 0;

        while (i <= right) {
            if(arr[i] == 0) {
                // swap 0s with the element at the left pointer
                int temp = arr[i];
                arr[i] = arr[left];
                arr[left] = temp;
                left++;
                i++;
            } else if (arr[i] == 2) {
                // swap 2s with the element at the right pointer
                int temp = arr[i];
                arr[i] = arr[right];
                arr[right] = temp;
                right--;
            } else {
                i++;
            }
        }

        return arr;
    }
}
