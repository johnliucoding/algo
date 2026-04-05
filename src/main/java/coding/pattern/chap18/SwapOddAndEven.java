package coding.pattern.chap18;

public class SwapOddAndEven {

    public static int swap(int num) {
        int even_mask = 0b01010101010101010101010101010101;
        int odd_mask =  0b10101010101010101010101010101010;
        int even_bits = num & even_mask;
        int odd_bits = num & odd_mask;
        // shift the even bits to the left, the odd bits to the right, and
        // merge these shifted values together
        return (even_bits << 1) | (odd_bits >>> 1);
    }
}
