package ch8.traversal;

import java.util.function.BiConsumer;

/**
 * @author Liu Xiaodong
 * @since 2024/8/29 4:44 PM
 */
public class EulerTourBinary {

    public static enum VisitType {
        PRE, IN, POST
    }

    public static void eulerTourBinary(TreeNode root, BiConsumer<TreeNode, VisitType> action) {
        action.accept(root, VisitType.PRE);
        if (root.left != null) {
            eulerTourBinary(root.left, action);
        }
        action.accept(root, VisitType.IN);
        if (root.right != null) {
            eulerTourBinary(root.right, action);
        }
        action.accept(root, VisitType.POST);
    }
}
