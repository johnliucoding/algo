package goodrich.ch3.arr;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Liu Xiaodong
 * @since 2024/7/6 4:13 PM
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] data = new int[10];
        final Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt();
        }
        final int[] orig = Arrays.copyOf(data, data.length);
        System.out.println("arrays equals before sort: " + Arrays.equals(orig, data));

        Arrays.sort(data);

        System.out.println("arrays equals after sort: " + Arrays.equals(orig, data));

        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(orig));
    }
}
