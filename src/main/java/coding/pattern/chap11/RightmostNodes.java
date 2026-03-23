package coding.pattern.chap11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaodong02
 */
public class RightmostNodes {

    record Pair(TreeNode<?> node, int level) {}


    public static  List<TreeNode<?>> rightmostNodes(TreeNode<?> root) {

        ArrayList<TreeNode<?>> results = new ArrayList<>();
        if(root == null) return results;

        ArrayDeque<Pair> queue = new ArrayDeque<>();
        int current_level = 0;
        queue.offer(new Pair(root, current_level));
        TreeNode<?> current_candidate = null;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            if(pair.level != current_level) {
                results.add(current_candidate);
                current_level = pair.level;
            }
            current_candidate = pair.node;


            if (pair.node.left != null) {
                queue.offer(new Pair(pair.node.left, current_level + 1));
            }
            if (pair.node.right != null) {
                queue.offer(new Pair(pair.node.right, current_level + 1));
            }

        }

        return results;
    }


    public static List<TreeNode<?>> rightmostNodes2(TreeNode<?> root) {
        var result = new ArrayList<TreeNode<?>>();
        if(root == null) return result;

        var queue = new ArrayDeque<TreeNode<?>>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int level_size = queue.size();
            // add all the non-null child nodes of the current level to the queue
            for (int i = 0; i < level_size; i++) {
                TreeNode<?> node = queue.poll();

                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                // record this level's last node to the result array
                if(i == level_size - 1) {
                    result.add(node);
                }
            }
        }

        return result;
    }
}
