package tree.traversal;

/**
 * @author Liu Xiaodong
 * @since 2024/12/30 11:17 AM
 */
public class BSTreeValidator {

    public static boolean isBstWithoutDuplicates(BSTree tree) {
        return isBstWithoutDuplicates(tree.root(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBstWithoutDuplicates(
            TreeNode node, int minAllowedKey, int maxAllowedKey) {
        if (node == null) {
            return true;
        }

        if (node.data < minAllowedKey || node.data > maxAllowedKey) {
            return false;
        }

        return isBstWithoutDuplicates(node.left, minAllowedKey, node.data - 1)
                && isBstWithoutDuplicates(node.right, node.data + 1, maxAllowedKey);
    }

    public static boolean isBstWithDuplicates(BSTree tree) {
        return isBstWithDuplicates(tree.root(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBstWithDuplicates(TreeNode node, int minAllowedKey, int maxAllowedKey) {
        if (node == null) {
            return true;
        }

        if (node.data < minAllowedKey || node.data > maxAllowedKey) {
            return false;
        }

        return isBstWithDuplicates(node.left, minAllowedKey, node.data)
                && isBstWithDuplicates(node.right, node.data, maxAllowedKey);
    }
}
