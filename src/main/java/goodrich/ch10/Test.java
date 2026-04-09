package goodrich.ch10;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liu Xiaodong
 * @since 2024/9/9 10:34 PM
 */
public class Test {

    public static void main(String[] args) {
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("a", 2);
        map.put("b", 3);
        map.put(null, 4);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }

    }
}
