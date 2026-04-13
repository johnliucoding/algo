package sort.nonComparable;

/**
 * @author Liu Xiaodong
 * @since 2025/1/11 3:47 PM
 */
public class RadixSortOtherBase {
    private final int base;
    public RadixSortOtherBase(int base) {
        this.base = base;
    }
    public void sort(int[] elements) {
        int max = getMax(elements);
        int numberOfDigits = getNumberOfDigits(max);

        int[] inputArray = elements;

        for (int digitIndex = 0; digitIndex < numberOfDigits; digitIndex++) {
            elements = sortByDigit(elements, digitIndex);
        }

        // Copy sorted elements back to input array
        System.arraycopy(elements, 0, inputArray, 0, elements.length);
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
        while (number >= base) {
            number /= base;
            numberOfDigits++;
        }
        return numberOfDigits;
    }
    private int calculateDivisor(int digitIndex) {
        int divisor = 1;
        for (int i = 0; i < digitIndex; i++) {
            divisor *= base;
        }
        return divisor;
    }

    private int[] countDigits(int[] elements, int divisor) {
        int[] counts = new int[base];
        for (int element : elements) {
            int digit = element / divisor % base;
            counts[digit]++;
        }
        return counts;

    }

    private int[] calculatePrefixSum(int[] counts) {
        int[] prefixSums = new int[base];
        prefixSums[0] = counts[0];
        for (int i = 1; i < base; i++) {
            prefixSums[i] = prefixSums[i - 1] + counts[i];
        }
        return prefixSums;
    }

    private int[] sortByDigit(int[] elements, int digitIndex) {
        int divisor = calculateDivisor(digitIndex);
        final int[] counts = countDigits(elements, divisor);
        int[] prefixSums = calculatePrefixSum(counts);

        int[] target = new int[elements.length];
        for (int i = elements.length - 1; i >= 0; i--) {
            int element = elements[i];
            int digit = element / divisor % base;
            target[--prefixSums[digit]] = element;
        }
        return target;
    }
}
