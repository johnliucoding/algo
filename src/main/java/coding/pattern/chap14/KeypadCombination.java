package coding.pattern.chap14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liuxiaodong02
 */
public class KeypadCombination {

    public static List<String> combinations(String digits) {
        var keypad_map = Map.of(
                '2', "abc",
                '3', "def",
                '4', "ghi",
                '5', "jkl",
                '6', "mno",
                '7', "pqrs",
                '8', "tuv",
                '9', "wxyz"
        );
        var result = new ArrayList<String>();
        dfs(0, new ArrayList<Character>(), digits.toCharArray(), keypad_map, result);
        return result;
    }
    private static String toStr(List<Character> charList) {
        StringBuilder sb = new StringBuilder();
        for (Character c : charList) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static void dfs(int start, List<Character> current_combination, char[] digits, Map<Character, String> keypad_map, List<String> result) {
        // terminal condition: if al digits have been considered, add the current combination to the output list
        if(current_combination.size() == digits.length) {
            result.add(toStr(current_combination));
            return;
        }
        for (char letter : keypad_map.get(digits[start]).toCharArray()) {

            // add the current letter
            current_combination.add(letter);
            // recursively explore all paths that branch from this combination
            dfs(start+1, current_combination, digits, keypad_map, result);
            // backtrack by removing the letter we just added
            current_combination.removeLast();
        }

    }


}
