package coding.pattern.chap6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuttingWoodsTest {

    @Test
    void cutting() {

        int cutting = CuttingWoods.cutting(new int[]{2, 6, 3, 8}, 7);
        assertEquals(3, cutting);
    }
}