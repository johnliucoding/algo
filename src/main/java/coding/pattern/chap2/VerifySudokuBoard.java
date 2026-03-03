package coding.pattern.chap2;

import java.util.HashSet;

/**
 * @author liuxiaodong02
 * @date 2026/3/3 17:00
 */
public class VerifySudokuBoard {

    // integer division and modular
    // division subgrid index
    // modular  index inside subgrid
    // grid scaling, up and down

    // time O(n^2)
    // space O(n^2) each array of sets contains n hash sets, and each hash sets is capable of growing to a size of n

    public static boolean verify(int[][] board) {

        // create hash sets for each row, column, and sub grid to keep track of numbers previously seen on any
        // given row, column, or sub grid
        @SuppressWarnings("unchecked")
        var rowHashSets = (HashSet<Integer>[]) new HashSet<?>[]{new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()};

        @SuppressWarnings("unchecked")
        var columnHashSets = (HashSet<Integer>[]) new HashSet<?>[]{new HashSet<>(), new HashSet<>(), new HashSet<>(),
                new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()};

        @SuppressWarnings("unchecked")
        var subGridHashSets =  (HashSet<Integer>[][]) new HashSet<?>[][]{
                {new HashSet<>(), new HashSet<>(), new HashSet<>()},
                {new HashSet<>(), new HashSet<>(), new HashSet<>()},
                {new HashSet<>(), new HashSet<>(), new HashSet<>()}};

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int score = board[row][col];
                if(score == 0) {
                    continue;
                }

                // check if score has been seen in the current row, column or sub grid
                if (rowHashSets[row].contains(score)) {
                    return false;
                }

                if(columnHashSets[col].contains(score)) {
                    return false;
                }

                var subGridRow = row / 3;
                var subGridCol = col / 3;
                if (subGridHashSets[subGridRow][subGridCol].contains(score)) {
                    return false;
                }

                // if we passed the above checks, mark this value as seen by adding
                // it to its corresponding hash sets
                rowHashSets[row].add(score);
                columnHashSets[col].add(score);
                subGridHashSets[subGridRow][subGridCol].add(score);
            }
        }
        return true;
    }
}
