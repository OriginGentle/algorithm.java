package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @since 2022/3/14-11:17
 */
public class Problem_0110_BalancedBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(process(root.left) - process(root.right)) <= 1 && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public static int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(process(root.left), process(root.right)) + 1;
    }
}
