package coding.pattern.chap8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liuxiaodong02
 */
public class TopK {

    private static final Logger log = LoggerFactory.getLogger(TopK.class);

    record Pair(String str, int freq) implements Comparable<Pair> {

        private static final Comparator<Pair> cmp = Comparator.comparing(Pair::freq).reversed().thenComparing(Pair::str);

        @Override
        public int compareTo(Pair other) {
            return cmp.compare(this, other);
        }
    }

    // time O(n) count frequency and heapify + O(freq*log(n)) poll freq elements from heap
    // space O(n)
    public static List<String> topKFrequent(List<String> strs, int k) {
        Map<String, Long> freq_map = strs.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        log.atInfo().setMessage("freq map: {}").addArgument(freq_map).log();

        // must use a comparable to use heapify
//        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(Pair::freq).reversed().thenComparing(Pair::str));
        List<Pair> list = freq_map.entrySet().stream().map(entry -> new Pair(entry.getKey(), entry.getValue().intValue())).toList();
        PriorityQueue<Pair> pq = new PriorityQueue<>(list);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            log.atInfo().setMessage("heap before poll: {}").addArgument(pq).log();
            result.add(pq.poll().str());
        }

        return result;

    }
}
