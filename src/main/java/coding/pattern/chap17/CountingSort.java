package coding.pattern.chap17;

import java.util.Arrays;

public class CountingSort {

    public static int[] sort(int[] input) {
        if(input == null || input.length == 0) {
            return input;
        }

        int max = Arrays.stream(input).max().getAsInt();
        int[] counts = new int[max + 1];
        // count occurrences of each element in 'input'
        for (int i : input) {
            counts[i]++;
        }
        int[] res =  new int[input.length];
        int resIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if(count > 0) {
                // build the sorted array by placing each index 'i' to 'counts[i]' times
                for (int i1 = 0; i1 < count; i1++) {
                    res[resIndex++] = i;
                }
            }
        }

        return res;
    }
}
