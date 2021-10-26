package com.training.day38;

/**
 * @author ycb
 * @since 2021/10/25-10:58
 */
public class Problem_0617_MergeTwoBinaryTrees {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode head = new TreeNode(root1.val + root2.val);
        head.left = mergeTrees(root1.left, root2.left);
        head.right = mergeTrees(root1.right, root2.right);
        return head;
    }
}
