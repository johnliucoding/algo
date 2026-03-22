package coding.pattern.dop;

import java.util.List;

public class Main {

    static void main() {
        var jobs = List.of(
                new Job(1, List.of("c#", "javascript")),
                new Job(2, List.of("c#", "java")),
                new Job(3, List.of("javascript")));

        var input = "[c#] and not [java]";
        var query = JqlParser.parse(input);
        var results = jobs.stream()
                .filter(j -> JqlInterpreter.check(query, j)).toList();
        for (Job j : results) {
            System.out.println(j);
        }
    }
}
