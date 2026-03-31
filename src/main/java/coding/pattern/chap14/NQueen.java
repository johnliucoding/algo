package coding.pattern.chap14;

import java.util.HashSet;

/**
 * @author liuxiaodong02
 */
public class NQueen {

    static int res;
    public static int nQueen(int n) {
        res = 0;
        var colSet = new HashSet<Integer>();
        var diagonalSet = new HashSet<Integer>();
        var antiDiagonalSet = new HashSet<Integer>();
        dfs(0, colSet, diagonalSet, antiDiagonalSet, n);
        return res;
    }

    private static void dfs(int row, HashSet<Integer> colSet, HashSet<Integer> diagonalSet, HashSet<Integer> antiDiagonalSet, int n) {
        // base case: if we have reached the end of the rows
        // we've placed all n queens
        if (row == n) {
            res++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int curr_diagonal = row - col;
            int curr_anti_diagonal = row + col;

            // if there are queens on the current column, diagonal or
            // anti-diagonal, skip this square
            if(colSet.contains(col)
            || diagonalSet.contains(curr_diagonal)
            || antiDiagonalSet.contains(curr_anti_diagonal)) {
                continue;
            }
            // place the queen by marking the current column,
            // diagonal, and anti-diagonal as occupied
            colSet.add(col);
            diagonalSet.add(curr_diagonal);
            antiDiagonalSet.add(curr_anti_diagonal);
            // recursively move to the next row to continue placing queens
            dfs(row + 1, colSet, diagonalSet, antiDiagonalSet, n);
            // backtrack by removing the current column, diagonal, anti-diagonal
            // from the hash sets
            colSet.remove(col);
            diagonalSet.remove(curr_diagonal);
            antiDiagonalSet.remove(curr_anti_diagonal);
        }
    }
}
