package coding.pattern.chap11;

import java.util.ArrayDeque;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class WidestBinaryTreeLevel {
    record Pair(TreeNode<?> node, int index){}
    public static int widestLevel(TreeNode<?> root) {
        int result = 0;
        if(root == null) return result;
        var queue= new ArrayDeque<Pair>();
        queue.offer(new Pair(root, 0));

        while(!queue.isEmpty()){

            int level_size = queue.size();
            int level_start_index = 0;
            for(int i = 0; i < level_size; i++){
                Pair pair = queue.poll();
                var node = pair.node;
                var index = pair.index;

                if(node.left != null) {
                    queue.offer(new Pair(node.left, 2*index + 1));
                }
                if(node.right != null) {
                    queue.offer(new Pair(node.right, 2*index + 2));
                }
                if(i == 0) {
                    level_start_index = index;
                }
                if(i == level_size - 1){
                    result = Math.max(result, index - level_start_index + 1);

                }
            }

        }

        return result;
    }

    public static int widestLevel2(TreeNode<?> root) {

        record  Pair(TreeNode<?> node, int index){}
        if(root == null) return 0;
        int max_width = 0;
        var queue= new ArrayDeque<Pair>();
        queue.offer(new Pair(root, 0)); // store [node, index] pair
        while(!queue.isEmpty()){
            int level_size = queue.size();
            // set the 'leftmost_index' to the index of the first node in
            // this level. start 'rightmost_index' as the same point as
            // 'leftmost_index' and update it as we traverse the level,
            // eventually positioning it at the last node.
            int leftmost_index = queue.peek().index();
            int rightmost_index = leftmost_index;
            for (int i = 0; i < level_size; i++) {
                Pair pair = queue.poll();
                var node = pair.node;
                var index = pair.index;
                if(node.left != null) {
                    queue.offer(new Pair(node.left, 2*index + 1));
                }
                if(node.right != null) {
                    queue.offer(new Pair(node.right, 2*index + 2));
                }
                rightmost_index = index;
            }
            max_width = Math.max(max_width, rightmost_index - leftmost_index + 1);

        }
        return max_width;
    }
}
