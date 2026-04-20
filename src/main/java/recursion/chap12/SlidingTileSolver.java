package recursion.chap12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public class SlidingTileSolver {

    public static boolean solve(Board board, int maxMoves) {
        ArrayList<DIRECTION> solutionMoves = new ArrayList<>();
        var solved = attemptMove(board, solutionMoves, maxMoves, null);

        if(solved) {
            board.displayBord();
            for (DIRECTION direction : solutionMoves) {
                System.out.println("Move " + direction);
                board.makeMove(direction);
                System.out.println();

                board.displayBord();
                System.out.println();
            }
            System.out.println("Solved in " + solutionMoves.size() + " moves: ");
            System.out.println(String.join(", ", solutionMoves.toString()));
            return true;
        } else {
            return false;
        }
    }

    public static boolean attemptMove(Board board, List<DIRECTION> movesMade, int movesRemaining, DIRECTION prevMove) {
        if(movesRemaining < 0) {
            // BASE CASE
            // ran out of moves
            return false;
        }
        if(board.isSolved()) {
            return true;
        }

        // RECURSIVE CASE
        for (DIRECTION direction : board.getValidMoves(prevMove)) {
            board.makeMove(direction);
            movesMade.add(direction);
            if(attemptMove(board, movesMade,  movesRemaining-1, direction)) {
                board.undoMove(direction); // reset to the original puzzle
                return true;
            }
            board.undoMove(direction);
            movesMade.removeLast(); // remove the last move since it was undone
        }
        return false;
    }
    

    public enum DIRECTION {UP, DOWN, LEFT, RIGHT}

    public static class Board {
        static Random random = new Random(2); //
        static int SIZE = 4; // the board is SIZE * SIZE spaces.
        static int blank = 0;
        static int DIFFICULTY = 40; // how many random slides a puzzle starts with.

        private final int[] data;
        public Board() {
            int len = SIZE*SIZE;

            this.data = new int[len];
            for (int i = 1; i < len; i++) {
                this.data[i-1] = i;
            }
            this.data[len-1] = blank;
        }
        public void setUpPuzzle() {
            for (int i = 0; i < DIFFICULTY; i++) {
                List<DIRECTION> validMoves = getValidMoves(null);
                makeMove(validMoves.get(random.nextInt(validMoves.size())));
            }
        }
        public boolean isSolved() {
            int[] solved = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
            return Arrays.equals(this.data, solved);
        }
        public void displayBord() {
            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    if(this.data[y * SIZE + x] == blank) {
                        System.out.print("__ ");
                    } else {
                        int cell = this.data[y * SIZE + x];
                        
                        System.out.print(justTwo(cell) + " ");
                    }
                }
                System.out.println();
            }
        }

        public int[] findBlankSpace() {
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if(this.data[y*SIZE + x] == blank) {
                        return new int[] {x, y};
                    }
                    
                }
            }
            return null;
        }

        public void makeMove(DIRECTION move) {
            int[] blankLocation = findBlankSpace();
            int bx = blankLocation[0];
            int by = blankLocation[1];
            int blankIndex = by * SIZE + bx;

            int tileIndex = switch (move) {
                case UP -> (by + 1) * SIZE + bx;
                case LEFT -> by * SIZE + (bx + 1);
                case DOWN -> (by - 1) * SIZE + bx;
                case RIGHT -> by * SIZE + (bx - 1);
            };
            
            // swap
            int temp = this.data[tileIndex];
            this.data[tileIndex] = this.data[blankIndex];
            this.data[blankIndex] = temp;


        }

        public void undoMove(DIRECTION move) {
            switch (move) {
                case UP:
                    makeMove(DIRECTION.DOWN);
                    break;
                case DOWN:
                    makeMove(DIRECTION.UP);
                    break;
                case LEFT:
                    makeMove(DIRECTION.RIGHT);
                    break;
                case RIGHT:
                    makeMove(DIRECTION.LEFT);
                    break;
                default:
                    break;
            }

        }

        public List<DIRECTION> getValidMoves(DIRECTION prevMove) {
            int[] blankLocation = findBlankSpace();
            int blankx = blankLocation[0];
            int blanky = blankLocation[1];

            var list = new ArrayList<DIRECTION>();
            if(blanky != SIZE - 1 && prevMove != DIRECTION.DOWN) {
                list.add(DIRECTION.UP);
            }
            if(blankx != SIZE - 1 && prevMove != DIRECTION.RIGHT) {
                list.add(DIRECTION.LEFT);
            }
            if(blanky!=0 && prevMove != DIRECTION.UP) {
                list.add(DIRECTION.DOWN);
            }
            if(blankx != 0 && prevMove != DIRECTION.LEFT) {
                list.add(DIRECTION.RIGHT);
            }

            return list;

        } 

        private String justTwo(int i) {

            var s = Integer.toString(i);
            if (s.length() == 2) {
                return s;
            } else {
                return " " + s;
            }
        }
    }
    static void main(String[] args) {
        Board board = new Board();
        board.setUpPuzzle();

        board.displayBord();

        int maxMoves = 10;
        while (!solve(board, maxMoves)) {
            maxMoves++;
        }
        System.out.println("used moves: " + maxMoves);
    }
}
