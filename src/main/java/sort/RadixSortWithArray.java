package sort;

/**
 * @author Liu Xiaodong
 * @since 2025/1/11 1:54 PM
 */
public class RadixSortWithArray {

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
            if (element > max) {
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

    private int[] countDigits(int[] elements, int divisor) {
        int[] counts = new int[10];
        for (int element : elements) {
            int digit = element / divisor % 10;
            counts[digit]++;
        }
        return counts;

    }

    private void sortByDigit(int[] elements, int digitIndex) {
        int divisor = calculateDivisor(digitIndex);
        final int[] counts = countDigits(elements, divisor);
        Bucket[] buckets = createBuckets(counts);


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

    private Bucket[] createBuckets(int[] counts) {
        Bucket[] buckets = new Bucket[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new Bucket(counts[i]);
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

        private final int[] elements;
        private int addIndex;

        public Bucket(int size) {
            elements = new int[size];
        }

        private void add(int element) {
            elements[addIndex] = element;
            addIndex++;
        }

        private int[] elements() {
            return elements;
        }
    }
}
