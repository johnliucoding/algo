package recursion.chap3;

/**
 * FloodFill
 */
public class FloodFill {

    public static void main(String[] args) {
        char[][] image = {
                "..########################...........".toCharArray(),
                "..#......................#...#####...".toCharArray(),
                "..#..........########....#####...#...".toCharArray(),
                "..#..........#......#............#...".toCharArray(),
                "..#..........########.........####...".toCharArray(),
                "..######......................#......".toCharArray(),
                ".......#..#####.....###########......".toCharArray(),
                ".......####...#######................".toCharArray()
        };
        int height = image.length;
        int width = image[0].length;

        printImage(image);
        floodFill(image, height, width, 3, 3, '.', 'o');
        printImage(image);

    }

    static void floodFill(char[][] image, int height, int width, int x, int y, char oldChar, char newChar) {
        if (oldChar == newChar || image[y][x] != oldChar) {
            // base case. already changed or can't change
            return;
        }
        image[y][x] = newChar;
        // uncomment to view each step:
        // printImage(image);

        // change teh neighboring characters
        if (y + 1 < height && image[y + 1][x] == oldChar) {
            // recursive case
            floodFill(image, height, width, x, y + 1, oldChar, newChar);
        }
        if (y - 1 >= 0 && image[y - 1][x] == oldChar) {
            // recursive case
            floodFill(image, height, width, x, y - 1, oldChar, newChar);
        }
        if (x + 1 < width && image[y][x + 1] == oldChar) {
            // recursive case
            floodFill(image, height, width, x + 1, y, oldChar, newChar);
        }
        if (x - 1 >= 0 && image[y][x - 1] == oldChar) {
            // recursive case
            floodFill(image, height, width, x - 1, y, oldChar, newChar);
        }
        // implicit base case
        return;

    }

    static void printImage(char[][] image) {
        for (char[] line : image) {
            for (char col : line) {
                System.out.print(col);
            }
            System.out.println();
        }
        System.out.println();

    }
}