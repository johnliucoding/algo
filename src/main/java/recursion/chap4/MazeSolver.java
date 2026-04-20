package recursion.chap4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MazeSolver {
    static char empty = ' ';
    static char start = 'S';
    static char exit = 'E'; 
    static char path = '@';
    public record Coordinate(int x, int y) {

        @Override
        public String toString() {
            return "(" +this.x() +", " + this.y() + ")";
        }
    }
    static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(System.getProperty("user.dir"));

        final URL resource = MazeSolver.class.getResource("/recursion/examplemaze.txt");
        final Path of = Path.of(resource.toURI());
        List<String> maze = Files.readAllLines(of);
     

        int height = maze.size();
        int width = maze.stream().map(String::length).max(Comparator.naturalOrder()).get();

        printMaze(maze);

        var startPosition = findStart(maze, height, width, start);
        System.out.println("starting coordinate: " + startPosition);

        // char c = getPixelAt(maze, Coordinate.of(2, 2));
        // System.out.println(c);
        // setPixelAt(maze, Coordinate.of(2, 2), 'U');
        // printMaze(maze);

        // when the algorithms back-track from a dead end to an earlier intersection,
        // it knows which paths it has tried before and can try a different one.
        var visitedPixel = new ArrayList<Coordinate>();

        solve(maze, height, width, startPosition, visitedPixel);

        printMaze(maze);

    }

    public static boolean solve(List<String> maze, int height, int width,
                                Coordinate current, List<Coordinate> visited) {
   
        if(getPixelAt(maze, current) == exit) {
            return true; // find the exit, return true.
        }

        setPixelAt(maze, current, path); // mark the path in the maze
        visited.add(current);
        // printMaze(maze) // uncomment to view each forward step.

        // explore the north neighboring point:
        var northY = current.y() + 1;
        var northPoint = new Coordinate(current.x(), northY);
        if((northY < height) && canVisit(maze, northPoint) && (visited.indexOf(northPoint) == -1)){
            // RECURSIVE CASE
            if(solve(maze, height, width, northPoint, visited)) {
                return true; // BASE CASE
            }
        }

        // explore the south neighboring point:
        var southY = current.y() - 1;
        var southPoint = new Coordinate(current.x(), southY);
        if ((southY > 0) && canVisit(maze, southPoint) && (visited.indexOf(southPoint) == -1)) {
            // RECURSIVE CASE
            if (solve(maze, height, width, southPoint, visited)) {
                return true; // BASE CASE
            }
        }

        // explore the west neighboring point:
        var westX = current.x() - 1;
        var westPoint = new Coordinate(westX, current.y());
        if ((westX > 0) && canVisit(maze, westPoint) && (visited.indexOf(westPoint) == -1)) {
            // RECURSIVE CASE
            if (solve(maze, height, width, westPoint, visited)) {
                return true; // BASE CASE
            }
        }

        // explore the east neighboring point:
        var eastX = current.x() + 1;
        var eastPoint = new Coordinate(eastX, current.y());
        if ((eastX < width) && canVisit(maze, eastPoint) && (visited.indexOf(eastPoint) == -1)) {
            // RECURSIVE CASE
            if (solve(maze, height, width, eastPoint, visited)) {
                return true; // BASE CASE
            }
        }

        // reset the empty space. BACKTRACKING
        setPixelAt(maze, current, empty);
        // printMaze(maze) // uncomment to view each backtack step.

        return false; // BASE CASE

    }

    public static boolean canVisit(List<String> maze, Coordinate coordinate) {
         return   (getPixelAt(maze, coordinate) == empty) || (getPixelAt(maze, coordinate) == exit);
    }

    public static void printMaze(List<String> maze) {
        maze.forEach(System.out::println);
    }

    public static char getPixelAt(List<String> maze, Coordinate coordinate) {
        return maze.get(coordinate.y()).charAt(coordinate.x());
    }

    public static void setPixelAt(List<String> maze, Coordinate coordinate, char newChar) {
        String row = maze.get(coordinate.y());
        var sb = new StringBuilder(row);
        sb.setCharAt(coordinate.x(), newChar);
        maze.set(coordinate.y(), sb.toString());
    }

    public static Coordinate findStart(List<String> maze, int height, int width, char c) {
       for(int h = 0; h < height; h++) {
        String row = maze.get(h);
        for(int w = 0; w < width; w++) {
            char currentChar = row.charAt(w);
            if(currentChar == c) {
                return new Coordinate(w, h);
            }
        }
       }
        return null;
    }
}
