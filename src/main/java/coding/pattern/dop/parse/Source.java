package coding.pattern.dop.parse;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @param string
 * @param index index into the string that points to where we are parsing right now
 */
public record Source(String string, int index) {

    Optional<ParseResult<String>> match(Pattern regexp) {

        Matcher matcher = regexp.matcher(string).region(index, string.length());
        if (matcher.lookingAt()) {
            int end = matcher.end();
            Source newSource = new Source(string, end);
            return Optional.of(new ParseResult<>(matcher.group(), newSource));
        }
        return Optional.empty();
    }
}
