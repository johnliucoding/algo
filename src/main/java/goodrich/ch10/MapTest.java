package goodrich.ch10;

import java.util.Iterator;

/**
 * @author Liu Xiaodong
 * @since 2024/9/9 9:55 PM
 */
public class MapTest {
    public static void main(String[] args) {
        final Map<String, Integer> scores = new SeparateChainingMap<>();

        scores.put("APpu Doe", 23);
        scores.put("Jane Doe", 34);
        scores.put("Hell Doe", 40);

//        System.out.println(scores.get("Hello Doe"));

        for (Map.Entry<String, Integer> next : scores) {
            System.out.println(next.getKey() + "=>" + next.getValue());
        }

    }
}
