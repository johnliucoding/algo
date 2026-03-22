package coding.pattern.dop;

import java.util.List;
import java.util.Objects;

public record Job(long id, List<String> tags) {
    public Job {
        Objects.requireNonNull(tags);
        tags = List.copyOf(tags);
    }
}
