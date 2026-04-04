package coding.pattern.chap16;

import java.util.Arrays;

public class Candies {

    public static int distributeCandies(int[] ratings) {
        int[] dist = new int[ratings.length];
        Arrays.fill(dist, 1);

        for (int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1]){
                dist[i] = dist[i-1] + 1;
            }
        }
        for (int i = ratings.length - 2; i > -1; i--) {
            if(ratings[i] > ratings[i+1] && dist[i] <= dist[i+1]){
                dist[i] =  dist[i+1] + 1;
            }
        }
        return Arrays.stream(dist).sum();
    }
}
