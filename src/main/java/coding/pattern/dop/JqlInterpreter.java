package coding.pattern.dop;

import java.util.Objects;

interface JqlInterpreter {
    // recursive match
    static boolean check(JqlSyntax node, Job j) {
        Objects.requireNonNull(node);
        Objects.requireNonNull(j);
        return switch (node) {
            case Tag(String value)  -> j.tags().contains(value);
            case And(JqlSyntax left, JqlSyntax right) -> check(left, j) && check(right, j);
            case Not(JqlSyntax operand) -> !check(operand, j);
        };
    }


}
