package dop.parser.parse;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static final Logger log = LoggerFactory.getLogger(ParserTest.class);

    @Test
    void regexp() {

        var source = new Source("hello1 byte2", 0);
        var parser = Parser.regexp("hello[0-9]");
        var result = parser.parse(source);
        assertTrue(result.isPresent());
        assertEquals("hello1", result.get().value());
        assertEquals(6, result.get().source().index());
    }

    @Test
    void zeroOrMore() {
        Parser<String> letter = Parser.regexp("[a-z]");
        Parser<String> digit = Parser.regexp("[0-9]");
        Parser<String> letterOrDigit = letter.or(digit);
        Parser<List<String>> someLetterOrDigits = Parser.zeroOrMore(letterOrDigit);
        var source = new Source("hello1 byte2", 0);
        var result = someLetterOrDigits.parse(source);
        assertTrue(result.isPresent());
        ParseResult<List<String>> results = result.get();
        List<String> value = results.value();
        log.info("results: {}", value);


    }

    @Test
    void testBind() {
        var pair =  Parser.regexp("[0-9]+")
                .bind(first -> Parser.regexp(",")
                        .bind(_ -> Parser.regexp("[0-9]+")
                                .bind(second -> Parser.constant(List.of(first, second)))));
        var input = "12,34";
        Optional<ParseResult<List<String>>> result = pair.parse(new Source(input, 0));
        assertTrue(result.isPresent());
        List<String> value = result.get().value();
        assertEquals(List.of("12", "34"), value);
    }

    @Test
    void testBind2() {
        var pair = Parser.regexp("[0-9]+").bind(
                first -> Parser.regexp(",").and(Parser.regexp("[0-9]+")).
                        map(second -> List.of(first, second))
        );
    }
}