package chap2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Sort {
    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static <T extends Comparable<T>> void show(T[] a) {
        System.out.println(Arrays.toString(a));
    }
    private static <T extends Comparable<T>> boolean isSorted(T[] a) {
        // 验证数组a是从小到大排列的
        for (int i = 0; i < a.length - 1; i++) {
            if(less(a[i+1], a[i])) {
                return false;
            }
        }
        return true;
    }
}
