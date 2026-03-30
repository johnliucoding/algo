package coding.pattern.chap13;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;



/**
 * @author liuxiaodong02
 */
public class ShortestTransformationSequence {

    public static int shortestPath(String start, String end, List<String> dictionary) {
        var dict = new HashSet<>(dictionary);
        if(!dict.contains(start) || !dict.contains(end)) {
            return 0;
        }
        if(start.equals(end)) {
            return 1;
        }
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        var queue = new ArrayDeque<String>();
        queue.offer(start);
        var visited = new HashSet<String>();
        visited.add(start);
        int dist = 0;
        // use level-order traversal to find the shortest path from the start word to the end word
        while(!queue.isEmpty()) {

            int queueSize = queue.size();
            for(int i = 0; i < queueSize; i++) {
                String current = queue.poll();
                assert current != null;
                // if we found the end word, we've reached it via shortest path
                if(current.equals(end)) {
                    return dist + 1;
                }
                // generate all possible words that have a one-character difference to the current word
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < current.length(); j++) {
                    for (int c = 0; c < lowerCaseAlphabet.length(); c++) {
                        String nextWord = sb.append(current, 0, j)
                                .append(lowerCaseAlphabet.charAt(c))
                                .append(current, j + 1, current.length()).toString();
                        sb.setLength(0);
                        // if nextWord exists in the dict, it's neighbor of the current word
                        // if unvisited, add it to the queue to be processed in the next level
                        if(dict.contains(nextWord)
                        &&  !visited.contains(nextWord)) {
                            visited.add(nextWord);
                            queue.offer(nextWord);
                        }
                    }
                }
            }
            dist++;
        }
        // if there is no way to reach the end node, then no path exists
        return 0;
    }


}
