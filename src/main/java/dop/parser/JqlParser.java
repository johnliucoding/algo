package dop.parser;

public interface JqlParser {

    @FunctionalInterface
    interface Parser<T> {
        record Pair<E>(E result, String leftovers) {}
        Iterable<Pair<T>> parse(String input);
    }


    static JqlSyntax parse(String input) {
        return null;
    }
}
