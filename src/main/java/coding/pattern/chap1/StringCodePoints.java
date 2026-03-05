package coding.pattern.chap1;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author liuxiaodong02
 */
public class StringCodePoints {

    public static final String text = "Hello123🌍";
    public static final int[] codePoints = {72, 101, 108, 108, 111, 128512}; // "Hello😀"
    public static final String text2 = "A😀B🎉C";

    public static void length() {

        int len = text.codePointCount(0, text.length());
        System.out.println(len);
    }

    public static void toCodePointsArray() {
        int[] codePoints = text.codePoints().toArray();
        System.out.println(Arrays.toString(codePoints));
    }

    public static void codePointsToString() {
        String str = new String(codePoints, 0, codePoints.length);
        System.out.println(str);
    }

    public static void manulLooping() {
        for (int i = 0; i < text.length(); ) {
            int cp = text.codePointAt(i);
            System.out.println(Character.toChars(cp));
            i += Character.charCount(cp);
        }
    }

    public static void useOffsetByCodePoints() {
        String text = "Hello 😀 World";
        // Char indices: 0123456789...
        //               Hello 😀...
        //                     ^^^
        //                     6,7 (surrogate pair for emoji)

        // Start at index 0, move forward 6 code points
        int emojiIndex = text.offsetByCodePoints(0, 6);
        System.out.println(emojiIndex);  // 6 (char index of emoji start)

        // Get the emoji
        int emoji = text.codePointAt(emojiIndex);
        System.out.printf("U+%04X: %c%n", emoji, emoji);  // U+1F600: 😀

        // Move 1 more code point (past the emoji)
        int wIndex = text.offsetByCodePoints(emojiIndex, 2);
        System.out.println(wIndex);  // 9 (char index of 'W')
        System.out.println(text.charAt(wIndex));  // 'W'
    }

    //  use offsetByCodePoints() when you need to move by logical characters
    // text.offsetByCodePoints(0, 6)
    // start at char index 0, move forward 6 code points
    public static void loopWithIndexTracking() {
        for (int i = 0, cpIdx = 0; i < text2.length(); cpIdx++) {
            int cp = text2.codePointAt(i);

            System.out.printf("Code point %d at char index %d: U+%04X (%c)%n", cpIdx, i, cp, cp);
            // move to next code point
            i = text2.offsetByCodePoints(i, 1);
        }
    }

    public static void loopWithIndexBackwardTracking() {
        int charLen = text2.length();
        int cpCount = text2.codePointCount(0, charLen);

        for (int i = charLen, cpIdx = cpCount - 1; i > 0; cpIdx--) {
            i = text2.offsetByCodePoints(i, -1);
            int cp = text2.codePointAt(i);
            System.out.printf("Code point %d at char index %d: U+%04X (%c)%n", cpIdx, i, cp, cp);
        }
    }

    public static void loopWithIndexBackwardTracking2() {
        int charLen = text2.length();
        int cpCount = text2.codePointCount(0, charLen);

        for (int i = charLen, cpIdx = cpCount - 1; i > 0; cpIdx--) {

            int cp = text2.codePointBefore(i);
            System.out.printf("Code point %d at char index %d: U+%04X (%c)%n", cpIdx, i, cp, cp);
            i = text2.offsetByCodePoints(i, -1);
        }
    }

    public static void test() {
        int[] nums = {1, 3, 4};
        int i;
        for (i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            System.out.println(num);
        }
        System.out.println("idx: " + i);
    }

    public static void test2() {
        int[] nums = {1, 3, 4};
        int i;
        for (i = nums.length; i > 0; i--) {
            int num = nums[i - 1];
            System.out.println(num);
        }
        System.out.println("idx: " + i);
    }

    public static void safeSubStr() {
        String text = "ABC😀DEF";  // 7 code points, 8 chars

        // WRONG: splits in middle of emoji!
        String wrongSplit = text.substring(0, 4);  // "ABC?" (corrupted emoji)
        System.out.println(wrongSplit);
        // CORRECT: find offset for 4 code points
        int safeOffset = text.offsetByCodePoints(0, 4);
        String correctSplit = text.substring(0, safeOffset);  // "ABC😀"
        System.out.println(correctSplit);  // "ABC😀" (intact emoji)
    }



    public static void reverse() {
        String reserved = new StringBuilder(text).reverse().toString();
        System.out.println(reserved);
    }

    public static void filterCharacters() {
        String str = text.codePoints().filter(cp -> !Character.isDigit(cp))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(str);
    }


    static void main() {
        test();
        test2();
        length();
        toCodePointsArray();
        codePointsToString();
        manulLooping();
        loopWithIndexTracking();
        loopWithIndexBackwardTracking();
        reverse();
        filterCharacters();
        safeSubStr();
        useOffsetByCodePoints();
        loopWithIndexBackwardTracking2();
    }


    public static boolean isPalindrome(String str) {
        Objects.requireNonNull(str, "str must not be null");
        if(str.length() < 2) {
            return true;
        }
        int left = 0;
        int right = str.offsetByCodePoints(str.length(), -1);

        while (left < right) {
            // skip non-alphanumeric characters from the left
            while(left < right && !isalnum(str.codePointAt(left))) {
                left = str.offsetByCodePoints(left, 1);
            }
            // skip non-alphanumeric characters from the right
            while(left < right && !isalnum(str.codePointAt(right))) {
                right = str.offsetByCodePoints(right, -1);
            }
            // if the characters at the left and right pointers don't match
            // the string is not a palindrome
            if(str.codePointAt(left) != str.codePointAt(right)) {
                return false;
            } else {
                left = str.offsetByCodePoints(left, 1);
                right = str.offsetByCodePoints(right, -1);
            }

        }
        return true;
    }

    private static boolean isalnum(int c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }
}
