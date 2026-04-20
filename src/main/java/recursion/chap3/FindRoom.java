package recursion.chap3;

import static recursion.chap3.FloodFill.printImage;

public class FindRoom {
    
    public static void main(String[] args) {
        char[][] image = {
                "...##########....................................".toCharArray(),
                "...#........#....####..................##########".toCharArray(),
                "...#........#....#..#...############...#........#".toCharArray(),
                "...##########....#..#...#..........#...##.......#".toCharArray(),
                ".......#....#....####...#..........#....##......#".toCharArray(),
                ".......#....#....#......############.....##.....#".toCharArray(),
                ".......######....#........................##....#".toCharArray(),
                ".................####........####..........######".toCharArray()
        };
        int height = image.length;
        int width = image[0].length;

        int numOfFill = 0;
//        printImage(image);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if(image[h][w] == '.'){
                    floodFill(image, height, width, w, h, '.', '#');
                    numOfFill++;
                }
            }
        }
//        printImage(image);
        // exclude the outer
        System.out.println(numOfFill-1);
   

    }

    static void floodFill(char[][] image, int height, int width, int x, int y, char oldChar, char newChar) {
        if (oldChar == newChar || image[y][x] != oldChar) {
            // base case. already changed or can't change
            return;
        }
        image[y][x] = newChar;
        // uncomment to view each step:
//         printImage(image);

        // change the neighboring characters
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
}
