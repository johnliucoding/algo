package coding.pattern.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorExample {

    static void invertMap() {
        Collection<String> strings =
                List.of("two", "three", "four",
                        "five", "six", "seven", "eight",
                        "nine", "ten", "eleven", "twelve");

        Map<Integer, Long> histogram =
                strings.stream()
                        .collect(
                                Collectors.groupingBy(
                                        String::length,
                                        Collectors.counting()));

        // invert the map
        Map<Long, List<Integer>> map = histogram.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));

        map.forEach((key, value) -> IO.println(key + " :: " + value));

        Map.Entry<Long, List<Integer>> result =
                map.entrySet().stream()
                        .max(Map.Entry.comparingByKey())
                        .orElseThrow();

        IO.println("result = " + result);
    }

    static void mappingCollector() {
        Collection<String> strings =
                List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve");

        List<String> result =
                strings.stream()
                        .collect(
                                Collectors.mapping(String::toUpperCase, Collectors.toList()));

        IO.println("result = " + result);
    }

    static void flatMappingCollector() {
        Collection<String> strings =
                List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                        "ten", "eleven", "twelve");

        List<String> result2 =
                strings.stream()
                        .collect(
                                Collectors.flatMapping(str ->
                                                Arrays.stream(str.split(""))
                                        , Collectors.toList()));

        IO.println("result2 = " + result2);
    }

    static void main() {
        mappingCollector();
        flatMappingCollector();
    }
}
