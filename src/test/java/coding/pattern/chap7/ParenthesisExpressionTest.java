package coding.pattern.chap7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuxiaodong02
 */
class ParenthesisExpressionTest {

    @Test
    void isValid() {
        assertTrue(ParenthesisExpression.isValid("([]{})"));
        assertFalse(ParenthesisExpression.isValid("([]{)}"));

        assertFalse(ParenthesisExpression.isValid("("));
        assertFalse(ParenthesisExpression.isValid("}"));

    }
}