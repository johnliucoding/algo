package coding.pattern.chap9;

/**
 * @author liuxiaodong02
 */
public record Interval(int start, int end) {
    public Interval {
        if (start > end) {
            throw new IllegalArgumentException("start must <= end");
        }
    }
}
