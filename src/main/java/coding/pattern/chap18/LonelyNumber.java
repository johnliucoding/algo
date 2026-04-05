package coding.pattern.chap18;

public class LonelyNumber {

    public static int lonelyNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
