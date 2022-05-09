package com.leetcode.problem_0001_0200;

/**
 * @author ycb
 * @date 2022/3/20-13:21
 */
public class Problem_0111_MinimumDepthOfBinaryTree {

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

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    private static int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) return 1;
        int ans = Integer.MAX_VALUE;
        if (root.left != null) {
            ans = Math.min(ans, process(root.left));
        }
        if (root.right != null) {
            ans = Math.min(ans, process(root.right));
        }
        return ans + 1;
    }
}
