package coding.pattern.chap16;

import java.util.Arrays;

public class GasStation {

    public static int index(int[] gas, int[] cost) {
        // if the total gas is less than the total cost, completing the circuit is impossible
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1;
        }
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i]- cost[i];
            // if our tank has negative gas, we cannot continue through the circuit from the current
            // start point, nor from any station before or including the current station 'i'
            if (tank < 0) {
                // set the next station as the new start point and reset the tank
                start = i + 1;
                tank = 0;
            }
        }
        return start;
    }
}
