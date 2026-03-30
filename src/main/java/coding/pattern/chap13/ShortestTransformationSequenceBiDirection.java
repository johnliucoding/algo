package coding.pattern.chap13;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class ShortestTransformationSequenceBiDirection {

    public static int shortestPath(String start, String end, List<String> dictionary) {

        var dict = new HashSet<>(dictionary);
        if(!dict.contains(start) || !dict.contains(end)) {
            return 0;
        }
        if(start.equals(end)) {
            return 1;
        }

        var startQueue = new ArrayDeque<String>();
        startQueue.offer(start);
        var startSet = new HashSet<>(startQueue);
        var startLevel = 0;

        var endQueue = new ArrayDeque<String>();
        endQueue.offer(end);
        var endSet = new HashSet<>(endQueue);
        var endLevel = 0;

        // perform a level-order traversal from the start word and another from the end word
        while(!startQueue.isEmpty() && !endQueue.isEmpty()) {
            // explore the next level of the traversal that starts from the start word
            // if it meets the other traversal, the shortest path between 'start' and 'end'
            // has been found
            startLevel++;
            if(exploreLevel(startQueue, startSet, endSet, dict)) {
                return startLevel + endLevel + 1;
            }
            // explore the next level of the traversal that starts from the end word
            endLevel++;
            if(exploreLevel(endQueue, endSet, startSet, dict)) {
                return startLevel + endLevel + 1;
            }
        }
        // if the traversals never met, then no path exists
        return 0;
    }

    private static boolean exploreLevel(ArrayDeque<String> queue, HashSet<String> visited, HashSet<String> otherVisited, HashSet<String> dict) {
        String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz";
        int queueSize = queue.size();
        for (int i = 0; i < queueSize; i++) {
            String current = queue.poll();
            assert current != null;
            // generate all possible words that have a one-character difference to the current word
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < current.length(); j++) {
                for (int c = 0; c < lowerCaseAlphabet.length(); c++) {
                    String nextWord = sb.append(current, 0, j)
                            .append(lowerCaseAlphabet.charAt(c))
                            .append(current, j + 1, current.length()).toString();
                    sb.setLength(0);
                    // if next_word has been visited during the other traversal, it means both searches have met
                    if(otherVisited.contains(nextWord)) {
                        return true;
                    }
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
        return false;
    }
}
