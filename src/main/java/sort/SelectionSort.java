package sort;

public class SelectionSort {

    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        // swapping smallest to the front
        for (int i = 0; i < arr.length; i++) {
            // find smallest
            var smallest = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[smallest]) < 0) {
                    smallest = j;
                }
            }

            swap(arr, i, smallest);
        }
    }

    public static <T extends Comparable<? super T>> void selectionSort2(T[] arr) {
        // swapping smallest to the front
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) { // 最后一个元素用不着排
            // find smallest
            var smallest = i;
            for (int j = i + 1; j < len; j++) {  // 第一个已经设置为最小值，不用再比较
                if (arr[j].compareTo(arr[smallest]) < 0) {
                    smallest = j;
                }
            }

            swap(arr, i, smallest);
        }
    }

    public static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
