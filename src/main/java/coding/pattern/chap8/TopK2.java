package coding.pattern.chap8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liuxiaodong02
 */
public class TopK2 {
    private static final Logger log = LoggerFactory.getLogger(TopK2.class);

    record Pair(String str, int freq) implements Comparable<Pair> {

        // 从小到大排
        private static final Comparator<Pair> cmp = Comparator.comparing(Pair::freq).thenComparing(Pair::str);

        @Override
        public int compareTo(Pair other) {
            return cmp.compare(this, other);
        }
    }

    public static List<String> topk(List<String> list, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(k);
        Map<String, Long> freq_map = list.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        log.atInfo().setMessage("freq map: {}").addArgument(freq_map).log();
        freq_map.forEach((key, value) -> {
            Pair pair = new Pair(key, value.intValue());
            // if heap size exceed 'k', pop the lowest frequency string to ensure
            // the heap only contains the 'k' most frequent words
            if(pq.size() < k) {
                pq.add(pair);
            } else {
                if (pq.peek().compareTo(pair) < 0) {
                    pq.poll();
                    pq.add(pair);
                }
            }
            log.atInfo().setMessage("after every loop: {}").addArgument(pq).log();
        });
        // return the 'k' most frequent strings by popping the remaining 'k' strings
        // from the heap. Since we're using a min-heap, we need to reverse the result after popping
        // the elements to ensure the most frequent strings are listed first.
        var result = new ArrayList<String>();
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            result.add(pair.str);
        }
        return result.reversed();
    }

}
