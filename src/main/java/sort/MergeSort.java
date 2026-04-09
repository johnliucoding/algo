package sort;

import java.util.Arrays;

/**
 * @author Liu Xiaodong
 * @since 2025/1/12 5:46 PM
 */
public record MergeSort<T extends Comparable<T>>(T[] arr) {

    void merge(int left, int mid, int right) {
        // 左子数组区间为 [left, mid], 右子数组区间为 [mid+1, right]
        // 创建一个临时数组 tmp ，用于存放合并后的结果
        T[] temp = (T[]) new Object[right - left + 1];
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

    void mergeSort(int left, int right) {
        // 终止条件
        if (left >= right)
            return; // 当子数组长度为 1 时终止递归
        // 划分阶段
        int mid = left + (right - left) / 2; // 计算中点
        mergeSort(left, mid); // 递归左子数组
        mergeSort(mid + 1, right); // 递归右子数组
        // 合并阶段
        merge(left, mid, right);
    }
    public void sort() {
        mergeSort(0, arr.length - 1);
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
