package cs61b;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Liu Xiaodong
 * @date 2024/1/23 11:11 PM
 */
public class TreeMapTest {

    public static void main(String[] args) {
        final TreeMap<String, String> map = new TreeMap<>();
        map.put("09:00:00", "Chicago");
        map.put("09:00:03", "Phoenix");
        map.put("09:00:13", "Houston");
        map.put("09:00:59", "Chicago");
        map.put("09:01:10", "Houston");
        map.put("09:03:13", "Chicago");
        map.put("09:10:11", "Seattle");
        map.put("09:14:25", "Phoenix");
        map.put("09:19:32", "Chicago");
        map.put("09:19:46", "Chicago");
        map.put("09:21:05", "Chicago");
        map.put("09:22:43", "Seattle");
        map.put("09:22:54", "Seattle");
        map.put("09:25:52", "Chicago");
        map.put("09:35:21", "Chicago");
        map.put("09:36:14", "Seattle");
        map.put("09:37:44", "Phoenix");


        final Map.Entry<String, String> floor = map.floorEntry("09:05:00");
        System.out.println(floor);
        final Map.Entry<String, String> lower = map.lowerEntry("09:05:00");
        System.out.println(lower);
        System.out.println(floor.equals(lower));

        final Map.Entry<String, String> floor2 = map.floorEntry("09:03:13");
        System.out.println(floor2);
        final Map.Entry<String, String> lower2 = map.lowerEntry("09:03:13");
        System.out.println(lower2);
        System.out.println(floor2.equals(lower2));


    }
}
