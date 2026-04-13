package sort;

public class BubbleSort {
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
       // swap the biggest smallest to end
        // there is leng - 1 bigger element
        for (int i = arr.length - 1;  i > 0; i--) {
            boolean did_swap = false;
            for (int j = 0; j < i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    did_swap = true;
                }
            }
            if (!did_swap) { // 如果没有进行过交换，说明已经排序完成
                break;
            }
        }
    }

}
