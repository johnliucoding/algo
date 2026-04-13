package sort.nonComparable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Xiaodong
 * @since 2025/1/10 5:57 PM
 */
public class RadixSortWithDynamicLists {

    public void sort(int[] elements) {
        int max = getMax(elements);
        int numberOfDigits = getNumberOfDigits(max);

        for (int digitIndex = 0; digitIndex < numberOfDigits; digitIndex++) {
            sortByDigit(elements, digitIndex);
        }
    }

    private int getMax(int[] elements) {
        int max = 0;
        for (int element : elements) {
            if(element > max) {
                max = element;
            }
        }
        return max;
    }

    private int getNumberOfDigits(int number) {
        int numberOfDigits = 1;
        while (number >= 10) {
            number /= 10;
            numberOfDigits++;
        }
        return numberOfDigits;
    }

    private void sortByDigit(int[] elements, int digitIndex) {

        Bucket[] buckets = createBuckets();
        int divisor = calculateDivisor(digitIndex);

        // 根据基数分配到每个bucket
        for (int element : elements) {
            int digit = element / divisor % 10;
            buckets[digit].add(element);
        }

        // 将bucket内容回填回原数组
        int targetIndex = 0;
        for (Bucket bucket : buckets) {
            for (int element : bucket.elements()) {
                elements[targetIndex] = element;
                targetIndex++;
            }
        }
    }

    private Bucket[] createBuckets() {
        Bucket[] buckets = new Bucket[10];
        for(int i = 0; i < 10; i++) {
            buckets[i] = new Bucket();
        }
        return buckets;
    }

    private int calculateDivisor(int digitIndex) {
        int divisor = 1;
        for (int i = 0; i < digitIndex; i++) {
            divisor *= 10;
        }
        return divisor;
    }

    static class Bucket {
        private final List<Integer> elements = new ArrayList<>();

        private void add(int element) {
            elements.add(element);
        }

        private List<Integer> elements() {
            return elements;
        }
    }
}
