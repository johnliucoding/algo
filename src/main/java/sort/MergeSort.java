package sort;



/**
 * @author Liu Xiaodong
 * @since 2025/1/12 5:46 PM
 */
public class MergeSort {

    private  static <T extends Comparable<? super T>>
    void merge(T[] arr, int left, int mid, int right) {
        // 左子数组区间为 [left, mid], 右子数组区间为 [mid+1, right]
        // 创建一个临时数组 tmp ，用于存放合并后的结果
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[right - left + 1];
        // 初始化左子数组和右子数组的起始索引
        int i = left, j = mid + 1, k = 0;
        // 当左右子数组都还有元素时，进行比较并将较小的元素复制到临时数组中
        while (i <= mid && j <= right) {
            if (arr[i].compareTo(arr[j]) <= 0)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        // 将左子数组和右子数组的剩余元素复制到临时数组中
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        // 将临时数组 tmp 中的元素复制回原数组 nums 的对应区间
        for (k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

    private  static <T extends Comparable<? super T>>
    void mergeSort(T[] arr, int left, int right) {
        // 终止条件
        if (left >= right)
            return; // 当子数组长度为 1 时终止递归
        // 划分阶段
        int mid = left + (right - left) / 2; // 计算中点
        mergeSort(arr, left, mid); // 递归左子数组
        mergeSort(arr, mid + 1, right); // 递归右子数组
        // 合并阶段
        merge(arr, left, mid, right);
    }

    public static  <T extends Comparable<? super T>>
    void sort(T[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

}
