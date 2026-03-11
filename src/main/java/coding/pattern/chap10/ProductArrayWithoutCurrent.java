package coding.pattern.chap10;

import java.util.Arrays;

/**
 * @author liuxiaodong02
 */
public class ProductArrayWithoutCurrent {

    public static int[] productArray(int[] nums) {
        int[] res  = new int[nums.length];
        Arrays.fill(res, 1);

        // populate the output with the running left product.
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // multiply the output with the running right product, from right to left
        int right_product = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] *= right_product;
            right_product *= nums[i];
        }

        return res;
    }
}
