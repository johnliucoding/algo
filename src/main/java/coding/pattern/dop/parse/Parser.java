package coding.pattern.dop.parse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;

@FunctionalInterface
public interface Parser<T> {



    Optional<ParseResult<T>> parse(Source source);

    static Parser<String> regexp(String regexp) {
        Pattern p = Pattern.compile(regexp);

        return source -> source.match(p);
    }
    static <U> Parser<U> constant(U input) {
        return source -> Optional.of(new ParseResult<>(input, source));
    }
    static <U> Parser<U> error(String message) {
        return source -> {
            throw new RuntimeException( message);
        };
    }
    static <U> Parser<List<U>> zeroOrMore(Parser<U> parser) {
        return source -> {
            List<U> result = new ArrayList<>();
            Optional<ParseResult<U>> item = parser.parse(source);
            while (item.isPresent()) {
                source = item.get().source();
                U value = item.get().value();
                result.add(value);
                item = parser.parse(source);
            }
            return Optional.of(new ParseResult<>(result, source));
        };
    }

    default Parser<T> or(Parser<T> other) {
        return source -> {
            var result = this.parse(source);
            if (result.isPresent()) {
                return result;
            } else {
                return other.parse(source);
            }
        };
    }

    default <U> Parser<U> bind(Function<T, Parser<U>> callback) {
        return (Parser<U>) source -> {
            Optional<ParseResult<T>> result = this.parse(source);


            if (result.isPresent()) {

                return callback.apply(result.get().value()).parse(result.get().source());

            } else  {
                return Optional.empty();
            }
        };
    }

    default <U> Parser<U> and(Parser<U> other) {
        return this.bind(_ -> other);
    }

    default <U> Parser<U> map(Function<T, U> callback) {
        return this.bind(val -> constant(callback.apply(val)));
    }

    static <U> Parser<U> maybe(Parser<U> parser) {
        return parser.or(constant(null));
    }

    default T parseStringToCompletion(String input) {
        Source source = new Source(input, 0);
        Optional<ParseResult<T>> result = parse(source);
        if (result.isEmpty()) {
            throw new RuntimeException("parse error at index 0");
        }
        int index = result.get().source().index();
        if(index != result.get().source().string().length()) {
            throw new RuntimeException("parse error at index " + index);
        }
        return result.get().value();
    }

    // boolean expression
    // and or not
    // ()
    // and or have different precedence


//    static Parser<T> buildParser() {
//
//    }
}
