package com.training.day37;

/**
 * @author ycb
 * @since 2021/10/25-8:52
 */
public class Problem_0226_InvertBinaryTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
}
