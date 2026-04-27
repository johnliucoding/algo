package semantic;

import java.util.HashMap;
import java.util.Map;

public final class TokenMap {

    static void main(String[] args) {
        final TokenMap map = new TokenMap();
        final Token<String> key = new Token<>();

        final Token<String> key2 = new Token<>();

        map.put(key, "Hello!");
        System.out.println(map.get(key));  // Hello!
        System.out.println(map.get(key2)); // null

        System.out.println(map);

        System.out.printf("");
    }

    private final Map<Token<?>, Object> map = new HashMap<>();

    @Override
    public String toString() {
        return "TokenMap{" +
                "map=" + map +
                '}';
    }

    public <T> void put(Token<T> key, T value) {
        this.map.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Token<T> key) {
        return (T) this.map.get(key);
    }

    public static final class Token<T> {
    }
}
