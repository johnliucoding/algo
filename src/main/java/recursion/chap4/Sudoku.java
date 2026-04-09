package recursion.chap4;


public class Sudoku {
    public static void main(String[] args) {
        int[][] grid = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

        // grid[y][x]
        // 9 rows, key y
        // 9 cols, key x
        // 9 boxes, key (y/3)*3 + x/3

        boolean[][] rows = new boolean[9][10]; // 9 rows, 10 cols
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int cell = grid[y][x];
                if (cell != 0) {
                    rows[y][cell] = true;
                    cols[x][cell] = true;
                    boxes[(y / 3) * 3 + x / 3][cell] = true;
                }
            }
        }

        printGrid(grid);
        solve(grid, rows, cols, boxes);
        System.out.println("====");
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
                if(x != 0 && x % 3 == 0) {
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

    public static boolean solve(int[][] grid, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (grid[y][x] == 0) {
                    for (int choice = 1; choice <= 9; choice++) {
                        if (!rows[y][choice] && !cols[x][choice] && !boxes[(y / 3) * 3 + x / 3][choice]) {

                            grid[y][x] = choice;
                            rows[y][choice] = true;
                            cols[x][choice] = true;
                            boxes[(y / 3) * 3 + x / 3][choice] = true;
                            if (solve(grid, rows, cols, boxes)) {
                                return true;
                            }
                            grid[y][x] = 0;
                            rows[y][choice] = false;
                            cols[x][choice] = false;
                            boxes[(y / 3) * 3 + x / 3][choice] = false;

                        }
                    }
                    return false;
                }
            }
        }
        return true;
        // solution: DFS + backtracking
        // related to N-Queens
    }

}
