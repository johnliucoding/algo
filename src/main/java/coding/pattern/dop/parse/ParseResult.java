package coding.pattern.dop.parse;

/**
 *
 * @param value hold value produced by the parser
 * @param source string with updated index position
 * @param <T>
 */
public record ParseResult<T>(T value, Source source) {
}
