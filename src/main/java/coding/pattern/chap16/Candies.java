package coding.pattern.chap16;

import java.util.Arrays;

public class Candies {

    public static int distributeCandies(int[] ratings) {
        int[] dist = new int[ratings.length];

        // ensure each child starts with 1 candy
        Arrays.fill(dist, 1);

        // first pass: for each child, ensure the child has more candies than their
        // left-side neighbor if the current child's rating is higher
        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1]){
                dist[i] = dist[i-1] + 1;
            }
        }
        // second pass: for each child, ensure the child has more candies than their right-side neighbor
        // if the current child's rating is higher
        for (int i = ratings.length - 2; i > -1; i--) {
            if(ratings[i] > ratings[i+1] && dist[i] <= dist[i+1]){
                dist[i] =  dist[i+1] + 1;
            }
        }
        return Arrays.stream(dist).sum();
    }
}
