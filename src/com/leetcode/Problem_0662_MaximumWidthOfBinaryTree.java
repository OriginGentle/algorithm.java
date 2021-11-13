package com.leetcode;

/**
 * @author ycb
 * @since 2021/10/18-15:56
 */
public class Problem_0662_MaximumWidthOfBinaryTree {

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

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int N = 1, M = 1;
        TreeNode cur = root;
        while (cur.left != null) {
            N++;
            cur = cur.left;
        }
        while (cur.right != null) {
            M++;
            cur = cur.right;
        }
        return (int) Math.pow(2, Math.max(N, M) - 1);
    }
}
