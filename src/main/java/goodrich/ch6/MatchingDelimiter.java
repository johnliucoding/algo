package goodrich.ch6;

/**
 * @author Liu Xiaodong
 * @since 2024/8/17 3:55 PM
 */
public class MatchingDelimiter {

    public static boolean isMatched(String exp) {
        String open = "({[";
        String close = ")}]";
        final LinkedStack<Character> stack = new LinkedStack<>();
        for (char c : exp.toCharArray()) {
            if (open.indexOf(c) != -1) {
                stack.push(c);
            } else if (close.indexOf(c) != -1) {
                if (stack.isEmpty()) {
                    return false;
                }
                final Character inStack = stack.pop(); // inStack always have value
                if (close.indexOf(c) != open.indexOf(inStack)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isHTMLMatched(String html) {
        final LinkedStack<String> stack = new LinkedStack<>();
        int i = html.indexOf('<');
        while (i != -1) {
            final int j = html.indexOf('>', i + 1);
            if (j == -1) {
                return false;
            }
            final String tag = html.substring(i + 1, j);
            if (!tag.startsWith("/")) {
                stack.push(tag);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!tag.substring(1).equals(stack.pop())) {
                    return false;
                }
            }
            i = html.indexOf("<", j + 1);
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }
}
