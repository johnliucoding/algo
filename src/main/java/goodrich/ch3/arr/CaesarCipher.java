package ch3.arr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Liu Xiaodong
 * @since 2024/7/11 8:14 PM
 */
public class CaesarCipher {
    private static final Logger log = LoggerFactory.getLogger(CaesarCipher.class);
    private final char[] encoder = new char[26];
    private final char[] decoder = new char[26];

    public CaesarCipher(int rotation) {
        for (int i = 0; i < 26; i++) {
            encoder[i] = (char)('A' + (i + rotation) % 26);
            decoder[i] = (char)('A' + (i - rotation + 26) % 26);
        }
    }

    private String transform(String input, char[] code) {
        final char[] ca = input.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            if(Character.isUpperCase(ca[i])) {
                final int pos = ca[i] - 'A';
                ca[i] = code[pos];
            }
        }
        return new String(ca);
    }

    public String encrypt(String input) {
        return transform(input, encoder);
    }

    public String decrypt(String input) {
        return transform(input, decoder);
    }

    public static void main(String[] args) {
        final CaesarCipher cipher = new CaesarCipher(3);
        log.info("encoder: {}", new String(cipher.encoder));
        log.info("decoder: {}", new String(cipher.decoder));
        var msg = "HELLO WORLD";
        final String coded = cipher.encrypt(msg);
        log.info("coded: {}", coded);
        final String ans = cipher.decrypt(coded);
        log.info("answer: {}", ans);
    }
}
