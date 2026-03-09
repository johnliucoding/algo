package coding.pattern.chap7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatingTest {

    @Test
    void eval() {

        int result = ExpressionEvaluating.eval("18-(7+(2-4))");
        assertEquals(13, result);
    }
}