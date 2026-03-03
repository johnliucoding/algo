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

    public static boolean verify(int[][] board) {

        // create hash sets for each row, column, and subgrid to keep track of numbers previously seen on any
        // given row, column, or subgrid
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

                rowHashSets[row].add(score);
                columnHashSets[col].add(score);
                subGridHashSets[subGridRow][subGridCol].add(score);
            }
        }
        return true;
    }
}
