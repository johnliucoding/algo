package recursion.chap4;


/**
 * SudokuSolver
 */
public class SudokuSolver {

    public static void main(String[] args) {
        int[][] grid = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

        printGrid(grid);
        solve(grid);
        System.out.println("==");
        printGrid(grid);
    }

    public static void printGrid(int[][] grid) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            if (y != 0 && y % 3 == 0) {
                sb.append("--------------------\n");
            }
            for (int x = 0; x < 9; x++) {
                int cell = grid[y][x];
                if (x != 0 && x % 3 == 0) {
                    sb.append("|");
                }
                if (cell == 0) {
                    sb.append(". ");
                } else {
                    sb.append(cell + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static boolean solve(int[][] grid) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (grid[y][x] == 0) {
                    for (int i = 1; i < 10; i++) {
                        if (possible(grid, x, y, i)) {
                            grid[y][x] = i; // make a decision to get a smaller problem
                            
                            if(solve(grid)) { // can this small problem works out, with boolean return value
                                return true;
                            }
                            grid[y][x] = 0; // backtracking if not work out
                            
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean possible(int[][] grid, int x, int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[y][i] == num) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (grid[i][x] == num) {
                return false;
            }
        }
        var x0 = (x / 3) * 3;
        var y0 = (y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[y0 + i][x0 + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}