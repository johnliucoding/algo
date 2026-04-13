package sort.production;

/**
 * @author Liu Xiaodong
 * @since 2025/1/13 10:10 PM
 *
 * Start       :  3  4  2  1  7  5  8  9  0  6
 * Select runs : (3  4)(2)(1  7)(5  8  9)(0  6)
 * Merge       : (2  3  4)(1  5  7  8  9)(0  6)
 * Merge       : (1  2  3  4  5  7  8  9)(0  6)
 * Merge       : (0  1  2  3  4  5  6  7  8  9)
 *
 */
public record NaturalMergeSort<T extends Comparable<T>>(T[] arr) {

    public void sort() {
        int numElements = arr.length;


        // identify runs
        int[] starts = new int[numElements + 1];
        int runCount = 0;
        starts[0] = 0;
        for (int i = 1; i <= numElements; i++) {
            if(i == numElements || arr[i].compareTo(arr[i-1]) < 0) {
                starts[++runCount] = i;
            }
        }

        // merge runs, until only 1 run is left
        T[] tmp = (T[]) new Object[numElements];

        T[] from = arr;
        T[] to = tmp;
        while (runCount > 1) {
            int newRunCount = 0;
            // merge two runs each
            for (int i = 0; i < runCount - 1; i += 2) {
                merge(from, to, starts[i], starts[i+1], starts[i+2]);
                starts[newRunCount++] = starts[i];

            }

            // Odd number of runs? Copy the last one
            if (runCount % 2 == 1) {
                int lastStart = starts[runCount - 1];
                System.arraycopy(from, lastStart, to, lastStart,
                        numElements - lastStart);
                starts[newRunCount++] = lastStart;
            }
            // Prepare for next round...
            starts[newRunCount] = numElements;
            runCount = newRunCount;
            // Swap "from" and "to" arrays
            T[] temp = from;
            from = to;
            to = temp;
        }

        // If final run is not in "elements", copy it there
        if (from != arr) {
            System.arraycopy(from, 0, arr, 0, numElements);
        }
    }

    private void merge(T[] source, T[] target, int startLeft, int startRight, int endRight) {
        int leftPos = startLeft;
        int rightPos = startRight;
        int targetPos = startLeft;

        // As long as both arrays contain elements...
        while (leftPos < startRight && rightPos < endRight) {
            // Which one is smaller?
            T leftValue = source[leftPos];
            T rightValue = source[rightPos];
            if (leftValue.compareTo(rightValue) <= 0) {
                target[targetPos++] = leftValue;
                leftPos++;
            } else {
                target[targetPos++] = rightValue;
                rightPos++;
            }
        }
        // Copy the rest
        while (leftPos < startRight) {
            target[targetPos++] = source[leftPos++];
        }
        while (rightPos < endRight) {
            target[targetPos++] = source[rightPos++];
        }
    }
}
