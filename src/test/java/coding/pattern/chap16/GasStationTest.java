package coding.pattern.chap16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasStationTest {

    @Test
    void index() {

        int[] gas = {2,5,1,3};
        int[] cost = {3,2,1,4};
        int idx = GasStation.index(gas, cost);
        assertEquals(1, idx);
    }
}