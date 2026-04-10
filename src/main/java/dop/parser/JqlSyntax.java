package dop.parser;

import java.util.Objects;

sealed interface JqlSyntax permits Tag, And, Not { }

record Tag(String value) implements JqlSyntax {
    Tag {
        Objects.requireNonNull(value);
    }
}
record And(JqlSyntax left, JqlSyntax right) implements JqlSyntax {
    And {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }
}
record Not(JqlSyntax operand) implements JqlSyntax {
    Not {
        Objects.requireNonNull(operand);
    }
}