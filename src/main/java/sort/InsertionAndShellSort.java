package sort;



public class InsertionAndShellSort {
    // aka. insertion in a sorted array

    public static <T extends Comparable<? super T>> void insertionSort(T[] data) {
        // position 0  as already sorted
        for (var i = 1; i< data.length; i++) {
            var current = data[i];
            // shift to find position for 'current' value
            int j = i;
            while (j > 0 && data[j-1].compareTo(current) > 0) {
                data[j] = data[j-1]; // 将 data[j-1]向右移动一位
                j--;
            }
            data[j] = current;       // 将 current赋值到正确位置
        }
    }

    public static <T extends Comparable<? super T>> void shellSort(T[] data) {
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (var i = gap; i< data.length; i++) {
                var current = data[i];
                // shift to find position for 'current' value
                int j = i;
                while (j >= gap && data[j-gap].compareTo(current) > 0) {
                    data[j] = data[j-gap];
                    j-=gap;
                }
                data[j] = current;
            }
        }

    }
}
